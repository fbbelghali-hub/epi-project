package com.example.epi.Mapper;

import com.example.epi.dto.CreateEpiRequestDTO;
import com.example.epi.dto.EpiRequestDTO;
import com.example.epi.dto.UpdateEpiRequestDTO;
import com.example.epi.entity.EpiRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EpiRequestMapper {
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "employee.lastName", target = "employeeName")
    @Mapping(source = "employee.firstName", target = "employeeFirstName")
    @Mapping(source = "employee.email", target = "email")
    @Mapping(source = "epi.id", target = "epiId")
    @Mapping(source = "epi.typeEPI", target = "epiType")
    EpiRequestDTO toDTO(EpiRequest entity);

    List<EpiRequestDTO> toDTOList(List<EpiRequest> list);

    EpiRequest toEntity(CreateEpiRequestDTO dto);

    EpiRequest toEntity(UpdateEpiRequestDTO dto);
}
