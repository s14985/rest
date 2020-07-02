import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Product } from '../models/product.model';
import { ProductOrder } from '../models/product-order.model';
import { ProductOrders } from '../models/product-orders.model';
import { Subject } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class EcommerceService {
  private productsUrl = 'http://localhost:8082/api/products';
  private ordersUrl = '/api/order';

  private product: Product;
  private productOrder: ProductOrder;
  private orders: ProductOrders = new ProductOrders();
  private total: number;

  private _productSubject = new Subject<Product>();
  private _productOrderSubject = new Subject<ProductOrder>();
  private _ordersSubject = new Subject();
  private _totalSubject = new Subject();

  ProductChanged$ = this._productSubject.asObservable();
  ProductOrderChanged$ = this._productOrderSubject.asObservable();
  OrdersChanged$ = this._ordersSubject.asObservable();
  TotalChanged$ = this._totalSubject.asObservable();

  constructor(private httpClient: HttpClient) {}

  set Product(value: Product) {
    this.product = value;
    this._productSubject.next();
  }

  get Product() {
    return this.product;
  }

  set SelectedProductOrder(value: ProductOrder) {
    this.productOrder = value;
    this._productOrderSubject.next();
  }

  get SelectedProductOrder() {
    return this.productOrder;
  }

  set ProductOrders(value: ProductOrders) {
    this.orders = value;
    this._ordersSubject.next();
  }

  get ProductOrders() {
    return this.orders;
  }

  get Total() {
    return this.total;
  }

  set Total(value: number) {
    this.total = value;
    this._totalSubject.next();
  }

  private getData() {
    return tap(
      (data) => {
        return data;
      },
      (error) => {
        return error;
      }
    );
  }

  getAllProducts() {
    return this.httpClient.get(this.productsUrl).pipe(
      this.getData()
    );
  }

  newProduct(product: Product) {
    return this.httpClient.post(this.productsUrl, product).pipe(
      this.getData()
    );
  }

  editProduct(product: Product) {
    return this.httpClient.put(this.productsUrl, product).pipe(
      this.getData()
    );
  }

  deleteProduct(id: number) {
    return this.httpClient.delete(this.productsUrl + '/' + id, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    }).pipe(
      this.getData()
    );
  }

  newOrder(order: ProductOrders) {
    return this.httpClient.post(this.ordersUrl, order).pipe(
      this.getData()
    );
  }

  finishOrder(id: number) {
    return this.httpClient.put(this.ordersUrl, id).pipe(
      this.getData()
    );
  }

  getProductDetails(id: number) {
    return this.httpClient.get(this.productsUrl + '/' + id).pipe(
      this.getData()
    );
  }
}
