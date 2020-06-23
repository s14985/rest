import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { OrdersComponent } from './items/orders/orders.component';
import { ItemsDialogComponent } from './items/items-dialog/items-dialog.component';
import { ProductsListComponent } from './items/products-list/products-list.component';
import { ProductDetailsComponent } from './items/product-details/product-details.component';
import { LoginComponent } from './login/login.component';
import { ErrorComponent } from './shared/error/error.component';

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
    component: OrdersComponent,
  },
  {
    path: 'items',
    component: ItemsDialogComponent,
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
    path: 'login',
    component: LoginComponent,
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
