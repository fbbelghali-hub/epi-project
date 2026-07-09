package com.example.epi.dto;

import com.example.epi.enums.TypeEPI;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEpiRequestDTO {

    private Long epiId;
    private Integer requestQuantity;

    private String reason;

    private String size;

}
