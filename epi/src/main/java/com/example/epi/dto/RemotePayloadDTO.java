package com.example.epi.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter

public class RemotePayloadDTO {
    @JsonProperty("Employe")
    private EmployeDTO Employe;

    @JsonProperty("CSP")
    private CspDTO CSP;
}
