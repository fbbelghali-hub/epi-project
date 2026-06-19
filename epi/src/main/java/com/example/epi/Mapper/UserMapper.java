package com.example.epi.Mapper;

import com.example.epi.dto.UserDTO;
import com.example.epi.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO dto);
}
