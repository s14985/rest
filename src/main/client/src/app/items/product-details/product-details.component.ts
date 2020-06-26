import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Product } from '../../models/product.model';
import { ProductOrder } from '../../models/product-order.model';
import { User } from '../../models/user.model';
import { ActivatedRoute, Router } from '@angular/router';
import { EcommerceService } from '../../services/ecommerce.service';
import { MatDialog } from '@angular/material/dialog';
import { AuthenticationService } from '../../services/authentication.service';
import { ItemsDialogComponent } from '../items-dialog/items-dialog.component';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss'],
})
export class ProductDetailsComponent implements OnInit, OnDestroy {
  private subProductChange: Subscription;
  private subProduct: Subscription;
  item: Product = new Product();
  order: ProductOrder;
  selectedProductOrder: ProductOrder;
  deleteError: boolean;
  suggestedItems: any[] = [];
  currentUser: User;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private ecommerceService: EcommerceService,
    private dialog: MatDialog,
    private authenticationService: AuthenticationService
  ) {}

  ngOnInit(): void {
    this.subProduct = this.activatedRoute.params.subscribe((params) => {
      this.loadProduct(+params['id']);
      this.currentUser = this.authenticationService.getCurrentUser();
      this.currentUser
        ? this.getSuggestedProducts(+params['id'])
        : this.getRandomProducts();
    });

    this.subProductChange = this.ecommerceService.ProductChanged$.subscribe(
      () => {
        this.loadProduct(this.ecommerceService.Product.id);
      }
    );
  }

  private getSuggestedProducts(id: number) {
    this.ecommerceService
      .findAllProductsFromOrdersByProductId(id)
      .subscribe((result: Product[]) => {
        this.suggestedItems = result.sort(() => 0.5 - Math.random());
      });
  }

  private getRandomProducts() {
    this.ecommerceService.findAllProducts().subscribe((result: Product[]) => {
      this.suggestedItems = result.sort(() => 0.5 - Math.random());
    });
  }

  private getSuggestedItems(a, key, id) {
    const seen = {};
    return a.filter(function (item) {
      const k = key(item);
      return item.product.id == id
        ? false
        : seen.hasOwnProperty(k)
        ? false
        : (seen[k] = true);
    });
  }
  private loadProduct(id: number) {
    this.ecommerceService
      .findProductById(id)
      .subscribe((result: Product) => (this.item = result));
  }

  ngOnDestroy(): void {
    this.subProductChange.unsubscribe();
    this.subProduct.unsubscribe();
  }

  private getConcatProductOrders(items: any[]) {
    let m = [];
    for (let i = 0; i < items.length; i++) {
      m = [].concat.apply(m, items[i].order.productOrders);
    }
    return m;
  }

  removeProduct() {
    this.activatedRoute.params.subscribe((params) => {
      this.ecommerceService.deleteProduct(+params['id']).subscribe(
        () => {
          this.router.navigateByUrl('/items');
        },
        () => {
          this.deleteError = true;
        }
      );
    });
  }

  addToCart(item: Product) {
    this.order = new ProductOrder(item, 1);
    this.ecommerceService.SelectedProductOrder = this.order;
    this.selectedProductOrder = this.ecommerceService.SelectedProductOrder;
  }

  openDialog(item: Product) {
    const dialogRef = this.dialog.open(ItemsDialogComponent, { data: item });
    dialogRef.afterClosed().subscribe((result) => {
      this.ecommerceService.Product = result;
    });
  }
}
