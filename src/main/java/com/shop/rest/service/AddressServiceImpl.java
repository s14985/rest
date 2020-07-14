package com.shop.rest.service;

import com.shop.rest.config.mapper.AddressMapper;
import com.shop.rest.dto.address.AddressDTO;
import com.shop.rest.exception.ResourceNotFoundException;
import com.shop.rest.model.Address;
import com.shop.rest.repository.AddressRepository;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
  private final AddressRepository addressRepository;
  private final AddressMapper addressMapper;

  private Address getModel(
    @Min(value = 1L, message = "Invalid address ID.") Long id
  ) {
    return addressRepository
      .findById(id)
      .orElseThrow(
        () -> new ResourceNotFoundException("address", "id", id.toString())
      );
  }

  /**
   * 4 degree nesting
   */
  @Override
  public AddressDTO getAddressById(Long id) {
    return addressMapper.toAddressDto(getModel(id));
  }
}
