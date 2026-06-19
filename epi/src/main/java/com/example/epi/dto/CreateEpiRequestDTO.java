package com.example.epi.dto;

import lombok.*;
 @AllArgsConstructor
 @Setter
 @NoArgsConstructor
 @Getter
public class CreateEpiRequestDTO {

    private Long epiId;
    private Integer requestQuantity;
    private String reason;
    private String size;
}
