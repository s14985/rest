package com.shop.rest.dto.address.output;

import lombok.Data;

@Data
public class AddressDTO {
  private Long id;
  private String street;
  private String suite;
  private String city;
  private String zipcode;
}
