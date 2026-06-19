package com.example.epi.Mapper;

import com.example.epi.entity.User;
import com.example.epi.dto.EmployeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RemoteUserMapper {

    @Mapping(source = "nom", target = "lastName")
    @Mapping(source = "prenom", target = "firstName")
    @Mapping(source = "poste_email", target = "email")
    User toEntity(EmployeDTO dto);
}
