package com.shop.rest.dto.user;

import com.shop.rest.dto.address.AddressDTO;
import lombok.Data;

@Data
public class UserWithAddressDTO {
  private Long id;
  private String username;
  private String firstName;
  private String lastName;
  private AddressDTO address;
}
