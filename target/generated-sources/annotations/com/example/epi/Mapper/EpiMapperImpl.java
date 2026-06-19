package com.example.epi.Mapper;

import com.example.epi.dto.CreateEpiRequestDTO;
import com.example.epi.dto.EpiDTO;
import com.example.epi.entity.Epi;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-19T09:46:10+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.19 (Microsoft)"
)
@Component
public class EpiMapperImpl implements EpiMapper {

    @Override
    public EpiDTO toDTO(Epi epi) {
        if ( epi == null ) {
            return null;
        }

        EpiDTO epiDTO = new EpiDTO();

        epiDTO.setId( epi.getId() );
        if ( epi.getTypeEPI() != null ) {
            epiDTO.setTypeEPI( epi.getTypeEPI().name() );
        }

        return epiDTO;
    }

    @Override
    public Epi toEntity(CreateEpiRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Epi.EpiBuilder epi = Epi.builder();

        return epi.build();
    }
}
