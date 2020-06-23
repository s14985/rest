import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ErrorComponent } from './shared/error/error.component';
import { FooterComponent } from './shared/footer/footer.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { ItemsDialogComponent } from './items/items-dialog/items-dialog.component';
import { OrdersComponent } from './items/orders/orders.component';
import { ProductDetailsComponent } from './items/product-details/product-details.component';
import { SuggestedProductComponent } from './items/product-details/suggested-product/suggested-product.component';
import { ProductsListComponent } from './items/products-list/products-list.component';
import { ShoppingCartComponent } from './items/shopping-cart/shopping-cart.component';
import { AuthenticationService } from './services/authentication.service';
import { EcommerceService } from './services/ecommerce.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    ErrorComponent,
    FooterComponent,
    NavbarComponent,
    ItemsDialogComponent,
    OrdersComponent,
    ProductDetailsComponent,
    SuggestedProductComponent,
    ProductsListComponent,
    ShoppingCartComponent,
  ],
  imports: [BrowserModule, AppRoutingModule],
  providers: [EcommerceService, AuthenticationService],
  bootstrap: [AppComponent],
  entryComponents: [ItemsDialogComponent],
})
export class AppModule {}
