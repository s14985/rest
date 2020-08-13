package com.shop.rest.config.mapper;

import com.shop.rest.dto.user.output.UserDTO;
import com.shop.rest.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
  UserDTO toUserDto(User model);
}
