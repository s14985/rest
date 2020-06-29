import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { OrderComponent } from './items/order/order.component';
import { ProductsListComponent } from './items/products-list/products-list.component';
import { ProductDetailsComponent } from './items/product-details/product-details.component';
import { ErrorComponent } from './shared/error/error.component';
import { ItemsComponent } from './items/items.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'home',
    component: HomeComponent,
  },
  {
    path: 'orders',
    component: OrderComponent,
  },
  {
    path: 'items',
    component: ItemsComponent,
    children: [
      {
        path: '',
        component: ProductsListComponent,
      },
      {
        path: ':id',
        component: ProductDetailsComponent,
      },
    ],
  },
  {
    path: '**',
    component: ErrorComponent,
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { scrollPositionRestoration: 'enabled' }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
