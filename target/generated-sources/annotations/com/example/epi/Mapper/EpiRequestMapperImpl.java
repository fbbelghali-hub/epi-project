package com.example.epi.Mapper;

import com.example.epi.dto.CreateEpiRequestDTO;
import com.example.epi.dto.EpiRequestDTO;
import com.example.epi.dto.UpdateEpiRequestDTO;
import com.example.epi.entity.Epi;
import com.example.epi.entity.EpiRequest;
import com.example.epi.entity.User;
import com.example.epi.enums.TypeEPI;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-03T10:19:17+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.19 (Microsoft)"
)
@Component
public class EpiRequestMapperImpl implements EpiRequestMapper {

    @Override
    public EpiRequestDTO toDTO(EpiRequest entity) {
        if ( entity == null ) {
            return null;
        }

        EpiRequestDTO epiRequestDTO = new EpiRequestDTO();

        epiRequestDTO.setEmployeeId( entityEmployeeId( entity ) );
        epiRequestDTO.setEmployeeName( entityEmployeeLastName( entity ) );
        epiRequestDTO.setEmployeeFirstName( entityEmployeeFirstName( entity ) );
        epiRequestDTO.setEmail( entityEmployeeEmail( entity ) );
        epiRequestDTO.setEpiId( entityEpiId( entity ) );
        epiRequestDTO.setEpiType( entityEpiTypeEPI( entity ) );
        epiRequestDTO.setId( entity.getId() );
        epiRequestDTO.setRequestDate( entity.getRequestDate() );
        epiRequestDTO.setRequestQuantity( entity.getRequestQuantity() );
        epiRequestDTO.setReason( entity.getReason() );
        if ( entity.getStatus() != null ) {
            epiRequestDTO.setStatus( entity.getStatus().name() );
        }
        epiRequestDTO.setSize( entity.getSize() );

        return epiRequestDTO;
    }

    @Override
    public List<EpiRequestDTO> toDTOList(List<EpiRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<EpiRequestDTO> list1 = new ArrayList<EpiRequestDTO>( list.size() );
        for ( EpiRequest epiRequest : list ) {
            list1.add( toDTO( epiRequest ) );
        }

        return list1;
    }

    @Override
    public EpiRequest toEntity(CreateEpiRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        EpiRequest.EpiRequestBuilder epiRequest = EpiRequest.builder();

        epiRequest.requestQuantity( dto.getRequestQuantity() );
        epiRequest.reason( dto.getReason() );
        epiRequest.size( dto.getSize() );

        return epiRequest.build();
    }

    @Override
    public EpiRequest toEntity(UpdateEpiRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        EpiRequest.EpiRequestBuilder epiRequest = EpiRequest.builder();

        epiRequest.requestQuantity( dto.getRequestQuantity() );
        epiRequest.reason( dto.getReason() );
        epiRequest.size( dto.getSize() );

        return epiRequest.build();
    }

    private Long entityEmployeeId(EpiRequest epiRequest) {
        User employee = epiRequest.getEmployee();
        if ( employee == null ) {
            return null;
        }
        return employee.getId();
    }

    private String entityEmployeeLastName(EpiRequest epiRequest) {
        User employee = epiRequest.getEmployee();
        if ( employee == null ) {
            return null;
        }
        return employee.getLastName();
    }

    private String entityEmployeeFirstName(EpiRequest epiRequest) {
        User employee = epiRequest.getEmployee();
        if ( employee == null ) {
            return null;
        }
        return employee.getFirstName();
    }

    private String entityEmployeeEmail(EpiRequest epiRequest) {
        User employee = epiRequest.getEmployee();
        if ( employee == null ) {
            return null;
        }
        return employee.getEmail();
    }

    private Long entityEpiId(EpiRequest epiRequest) {
        Epi epi = epiRequest.getEpi();
        if ( epi == null ) {
            return null;
        }
        return epi.getId();
    }

    private TypeEPI entityEpiTypeEPI(EpiRequest epiRequest) {
        Epi epi = epiRequest.getEpi();
        if ( epi == null ) {
            return null;
        }
        return epi.getTypeEPI();
    }
}
