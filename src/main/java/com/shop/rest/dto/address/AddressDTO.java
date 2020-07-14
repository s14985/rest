package com.shop.rest.dto.address;

import lombok.Data;

@Data
public class AddressDTO {
  private Long id;
  private String street;
  private String suite;
  private String city;
  private String zipcode;
}
