package com.example.epi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEpiRequestDTO {

    private Integer requestQuantity;

    private String reason;

    private String size;

}
