package com.example.epi.dto;
import lombok.*;
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Getter
public class UserDTO {
    private Long id;
    private String matricule;
    private String lastName;
    private String firstName;
    private String email;
    private String jobTitle;
    private String role;
}

