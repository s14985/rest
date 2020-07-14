package com.shop.rest.config.mapper;

import com.shop.rest.dto.user.UserDTO;
import com.shop.rest.dto.user.UserWithAddressDTO;
import com.shop.rest.model.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
  User toModel(UserWithAddressDTO dto);
  UserWithAddressDTO toUserWithAddressDto(User model);

  List<User> toModel(List<UserWithAddressDTO> dtos);
  List<UserWithAddressDTO> toUserWithAddressDto(List<User> models);

  Iterable<User> toModel(Iterable<UserWithAddressDTO> dtos);
  Iterable<UserWithAddressDTO> toUserWithAddressDto(Iterable<User> models);

  UserDTO toUserDto(User model);
}
