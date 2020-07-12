export class Product {
  id: number;
  name: string;
  price: number;
  picture: string;
  details: string;
  manufacturer: string;
  itemCode: string;
  color: string;
  material: string;

  constructor(
    id: number,
    name: string,
    price: number,
    picture: string,
    details: string,
    manufacturer: string,
    itemCode: string,
    color: string,
    material: string
  ) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.picture = picture;
    this.details = details;
    this.manufacturer = manufacturer;
    this.itemCode = itemCode;
    this.color = color;
    this.material = material;
  }
}
