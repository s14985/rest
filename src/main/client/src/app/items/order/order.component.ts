import {
  Component,
  EventEmitter,
  OnDestroy,
  OnInit,
  Output,
} from '@angular/core';
import { Subscription } from 'rxjs';
import { ProductOrders } from '../../models/product-orders.model';
import { EcommerceService } from '../../services/ecommerce.service';
import { Order } from '../../models/order.model';

@Component({
  selector: 'app-orders',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss'],
})
export class OrderComponent implements OnInit, OnDestroy {
  private subProductOrders: Subscription;
  orders: ProductOrders;
  order: any;
  total: number;
  paid: boolean;
  hide: boolean;
  finished: boolean;

  @Output() onOrderCanceled: EventEmitter<boolean>;
  @Output() onOrderFinished: EventEmitter<any>;

  constructor(private ecommerceService: EcommerceService) {
    this.hide = false;
    this.orders = this.ecommerceService.ProductOrders;
    this.onOrderCanceled = new EventEmitter<boolean>();
    this.onOrderFinished = new EventEmitter<any>();
  }

  ngOnInit(): void {
    this.total = this.ecommerceService.Total;
    this.paid = false;
    this.subProductOrders = this.ecommerceService.OrdersChanged$.subscribe(
      () => {
        this.orders = this.ecommerceService.ProductOrders;
      }
    );
  }

  pay() {
    this.paid = true;
    return this.ecommerceService.newOrder(this.orders).subscribe(
      (result: Order) => {
        this.order = result;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  back() {
    this.onOrderCanceled.emit(this.hide);
  }

  end() {
    this.onOrderCanceled.emit();
  }

  finish() {
    this.paid = false;
    this.finished = true;
    return this.ecommerceService.finishOrder(this.order.id).subscribe(
      (result: Order) => {
        this.order = result;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  ngOnDestroy(): void {
    this.subProductOrders.unsubscribe();
  }
}
