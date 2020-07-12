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
    manufacturer: new FormControl(
      this.data === null ? '' : this.data.manufacturer,
      [Validators.required]
    ),
    itemCode: new FormControl(this.data === null ? '' : this.data.itemCode, [
      Validators.required,
    ]),
    color: new FormControl(this.data === null ? '' : this.data.color, [
      Validators.required,
    ]),
    material: new FormControl(this.data === null ? '' : this.data.material, [
      Validators.required,
    ]),
  });

  name = this.addForm.controls['name'];
  price = this.addForm.controls['price'];
  picture = this.addForm.controls['pictureUrl'];
  details = this.addForm.controls['details'];
  manufacturer = this.addForm.controls['manufacturer'];
  itemCode = this.addForm.controls['itemCode'];
  color = this.addForm.controls['color'];
  material = this.addForm.controls['material'];

  constructor(
    private ecommerceService: EcommerceService,
    public dialog: MatDialogRef<ItemsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Product
  ) {}

  ngOnInit(): void {}

  onSubmit() {
    this.product = new Product(
      this.data.id,
      this.name.value,
      this.price.value,
      this.picture.value,
      this.details.value,
      this.manufacturer.value,
      this.itemCode.value,
      this.color.value,
      this.material.value
    );
    this.subNewProduct =
      this.data === null
        ? this.newProduct(this.product)
        : this.editProduct(this.product);
  }

  private editProduct(product: Product) {
    return this.ecommerceService.editProduct(product).subscribe(
      (result: Product) => {
        if (this.ifNoFieldErrors()) {
          this.dialog.close(result);
        }
      },
      () => {
        this.saveErrorMsg = true;
      }
    );
  }

  private newProduct(product: Product) {
    return this.ecommerceService.newProduct(product).subscribe(
      (result: Product) => {
        if (this.ifNoFieldErrors()) {
          this.dialog.close(result);
        }
      },
      () => {
        this.saveErrorMsg = true;
      }
    );
  }
  private ifNoFieldErrors() {
    return (
      !this.name.errors &&
      !this.price.errors &&
      !this.picture.errors &&
      !this.details.errors &&
      !this.manufacturer.errors &&
      !this.itemCode.errors &&
      !this.color.errors &&
      !this.material.errors
    );
  }
}
