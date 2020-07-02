import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Product } from '../../models/product.model';
import { ProductOrder } from '../../models/product-order.model';
import { ActivatedRoute, Router } from '@angular/router';
import { EcommerceService } from '../../services/ecommerce.service';
import { MatDialog } from '@angular/material/dialog';
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

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private ecommerceService: EcommerceService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.subProduct = this.activatedRoute.params.subscribe((params) => {
      this.getProductDetails(+params['id']);
    });

    this.subProductChange = this.ecommerceService.ProductChanged$.subscribe(
      () => {
        this.getProductDetails(this.ecommerceService.Product.id);
      }
    );
  }

  private getProductDetails(id: number) {
    this.ecommerceService.getProductDetails(id)
      .subscribe((result: any) => {
        this.item = result.product;
        this.suggestedItems = result.suggestedProducts.sort(() => 0.5 - Math.random());
      })
  }

  ngOnDestroy(): void {
    this.subProductChange.unsubscribe();
    this.subProduct.unsubscribe();
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
