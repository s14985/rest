import { Component, Inject, OnInit } from '@angular/core';
import { Product } from '../../models/product.model';
import { Subscription } from 'rxjs';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EcommerceService } from '../../services/ecommerce.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-items-dialog',
  templateUrl: './items-dialog.component.html',
  styleUrls: ['./items-dialog.component.scss'],
})
export class ItemsDialogComponent implements OnInit {
  product: Product;
  subNewProduct: Subscription;
  saveErrorMsg: boolean;
  loadErrorMsg: boolean;

  addForm = new FormGroup({
    name: new FormControl(this.data === null ? '' : this.data.name, [
      Validators.required,
    ]),
    price: new FormControl(this.data === null ? '' : this.data.price, [
      Validators.required,
    ]),
    pictureUrl: new FormControl(this.data === null ? '' : this.data.picture, [
      Validators.required,
    ]),
    details: new FormControl(this.data === null ? '' : this.data.details, [
      Validators.required,
    ]),
  });

  name = this.addForm.controls['name'];
  price = this.addForm.controls['price'];
  picture = this.addForm.controls['pictureUrl'];
  details = this.addForm.controls['details'];

  constructor(
    private ecommerceService: EcommerceService,
    public dialog: MatDialogRef<ItemsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Product
  ) {}

  ngOnInit(): void {}

  onSubmit() {
    this.subNewProduct =
      this.data === null ? this.newProduct() : this.editProduct();
  }

  private editProduct() {
    return this.ecommerceService.editProduct(this.product).subscribe(
      (result: Product) => {
        if (
          !this.name.errors &&
          !this.price.errors &&
          !this.picture.errors &&
          !this.details.errors
        ) {
          this.dialog.close(result);
        }
      },
      () => {
        this.saveErrorMsg = true;
      }
    );
  }

  private newProduct() {
    return this.ecommerceService.newProduct(this.product).subscribe(
      (result: Product) => {
        if (
          !this.name.errors &&
          !this.price.errors &&
          !this.picture.errors &&
          !this.details.errors
        ) {
          this.dialog.close(result);
        }
      },
      () => {
        this.saveErrorMsg = true;
      }
    );
  }
}
