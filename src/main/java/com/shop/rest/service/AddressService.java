package com.shop.rest.service;

import com.shop.rest.dto.address.AddressDTO;
import org.springframework.validation.annotation.Validated;

@Validated
public interface AddressService {
  AddressDTO getAddressById(Long id);
}
