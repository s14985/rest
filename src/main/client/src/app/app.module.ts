import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ErrorComponent } from './shared/error/error.component';
import { FooterComponent } from './shared/footer/footer.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { ItemsDialogComponent } from './items/items-dialog/items-dialog.component';
import { OrderComponent } from './items/order/order.component';
import { ProductDetailsComponent } from './items/product-details/product-details.component';
import { SuggestedProductComponent } from './items/product-details/suggested-product/suggested-product.component';
import { ProductsListComponent } from './items/products-list/products-list.component';
import { ShoppingCartComponent } from './items/shopping-cart/shopping-cart.component';
import { EcommerceService } from './services/ecommerce.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavItemComponent } from './shared/navbar/nav-item/nav-item.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ItemsComponent } from './items/items.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ErrorComponent,
    FooterComponent,
    NavbarComponent,
    ItemsDialogComponent,
    OrderComponent,
    ProductDetailsComponent,
    SuggestedProductComponent,
    ProductsListComponent,
    ShoppingCartComponent,
    NavItemComponent,
    ItemsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatSelectModule,
    MatCheckboxModule,
    ReactiveFormsModule,
    MatInputModule,
    FormsModule,
  ],
  providers: [EcommerceService],
  bootstrap: [AppComponent],
  entryComponents: [ItemsDialogComponent],
})
export class AppModule {}
