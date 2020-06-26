import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ProductOrders } from '../../models/product-orders.model';
import { ProductOrder } from '../../models/product-order.model';
import { Product } from '../../models/product.model';
import { Router } from '@angular/router';
import { EcommerceService } from '../../services/ecommerce.service';

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.scss'],
})
export class ProductsListComponent implements OnInit, OnDestroy {
  private subProducts: Subscription;
  private subOrders: Subscription;
  shoppingCartOrders: ProductOrders;
  selectedProductOrder: ProductOrder;
  product: Product;
  productOrders: ProductOrder[];
  products: Product[];
  name: string;
  checker: boolean;
  errorMsg: boolean;
  quantity: number;
  productSelected: boolean;

  constructor(
    private router: Router,
    private ecommerceService: EcommerceService
  ) {}

  ngOnInit(): void {
    this.productOrders = [];
    this.products = [];
    this.quantity = 0;
    this.checker = false;
    this.productSelected = false;
    this.loadProducts();
    this.loadOrders();
    this.subProducts = this.ecommerceService.ProductChanged$.subscribe(() => {
      this.loadProducts();
    });
  }

  ngOnDestroy(): void {
    this.subProducts.unsubscribe();
    this.subOrders.unsubscribe();
  }

  addToCart(order: ProductOrder) {
    this.ecommerceService.SelectedProductOrder = order;
    this.selectedProductOrder = this.ecommerceService.SelectedProductOrder;
  }

  private loadOrders() {
    this.subOrders = this.ecommerceService.OrdersChanged$.subscribe(() => {
      this.shoppingCartOrders = this.ecommerceService.ProductOrders;
    });
  }

  private loadProducts() {
    this.productOrders = [];
    this.ecommerceService.findAllProducts().subscribe(
      (result: any[]) => {
        this.products = result;
        this.products.forEach((product) => {
          this.productOrders.push(new ProductOrder(product, 0));
        });

        this.checker = this.productOrders.length === 0;
      },
      (error) => {
        this.errorMsg = true;
      }
    );
  }

  reset() {
    this.productOrders = [];
    this.loadProducts();
    this.ecommerceService.ProductOrders.productOrders = [];
    this.loadOrders();
    this.productSelected = false;
  }
}
