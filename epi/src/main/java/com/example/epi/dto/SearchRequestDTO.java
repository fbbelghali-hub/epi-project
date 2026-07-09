package com.example.epi.dto;

import com.example.epi.enums.RequestStatus;
import com.example.epi.enums.TypeEPI;
import lombok.*;

@Getter
@Setter
public class SearchRequestDTO {
    private String keyword;
    private RequestStatus status;
    private TypeEPI epiType;
}
