package com.example.epi.Mapper;

import com.example.epi.dto.EmployeDTO;
import com.example.epi.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-03T10:19:17+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.19 (Microsoft)"
)
@Component
public class RemoteUserMapperImpl implements RemoteUserMapper {

    @Override
    public User toEntity(EmployeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.lastName( dto.getNom() );
        user.firstName( dto.getPrenom() );
        user.email( dto.getPoste_email() );
        user.matricule( dto.getMatricule() );

        return user.build();
    }
}
