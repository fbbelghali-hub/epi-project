package com.example.epi.Mapper;

import com.example.epi.dto.CreateEpiRequestDTO;
import com.example.epi.dto.EpiDTO;
import com.example.epi.entity.Epi;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EpiMapper {
    EpiDTO toDTO(Epi epi);

    Epi toEntity(CreateEpiRequestDTO dto);
}
