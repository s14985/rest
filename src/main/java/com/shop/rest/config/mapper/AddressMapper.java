package com.shop.rest.config.mapper;

import com.shop.rest.dto.address.AddressDTO;
import com.shop.rest.model.Address;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {
  Address toModel(AddressDTO dto);
  AddressDTO toAddressDto(Address model);

  List<Address> toModel(List<AddressDTO> dtos);
  List<AddressDTO> toAddressDto(List<Address> models);

  Iterable<Address> toModel(Iterable<AddressDTO> dtos);
  Iterable<AddressDTO> toAddressDto(Iterable<Address> models);
}
