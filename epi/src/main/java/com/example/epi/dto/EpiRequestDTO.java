package com.example.epi.dto;

import com.example.epi.enums.TypeEPI;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EpiRequestDTO {
    private Long id;

    private LocalDate requestDate;

    private Integer requestQuantity;

    private String reason;

    private String status;

    private String size;

    private Long employeeId;

    private String employeeName;
    private String employeeFirstName;
    private Long epiId;

    private TypeEPI epiType;
    private String email;
}
