package com.shop.rest.config.mapper;

import com.shop.rest.dto.UserDTO;
import com.shop.rest.model.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
  User toModel(UserDTO dto);
  UserDTO toDto(User model);

  List<User> toModel(List<UserDTO> dtos);
  List<UserDTO> toDto(List<User> models);

  Iterable<User> toModel(Iterable<UserDTO> dtos);
  Iterable<UserDTO> toDto(Iterable<User> models);
}
