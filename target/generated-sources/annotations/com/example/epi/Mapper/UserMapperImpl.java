package com.example.epi.Mapper;

import com.example.epi.dto.UserDTO;
import com.example.epi.entity.User;
import com.example.epi.enums.Role;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-03T10:19:17+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.19 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setMatricule( user.getMatricule() );
        userDTO.setLastName( user.getLastName() );
        userDTO.setFirstName( user.getFirstName() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setJobTitle( user.getJobTitle() );
        if ( user.getRole() != null ) {
            userDTO.setRole( user.getRole().name() );
        }

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( dto.getId() );
        user.matricule( dto.getMatricule() );
        user.lastName( dto.getLastName() );
        user.firstName( dto.getFirstName() );
        user.email( dto.getEmail() );
        user.jobTitle( dto.getJobTitle() );
        if ( dto.getRole() != null ) {
            user.role( Enum.valueOf( Role.class, dto.getRole() ) );
        }

        return user.build();
    }
}
