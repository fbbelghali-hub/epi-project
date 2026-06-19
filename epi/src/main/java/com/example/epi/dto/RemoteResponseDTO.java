package com.example.epi.dto;

import lombok.*;
import java.util.List;
@Getter
@Setter
public class RemoteResponseDTO {
    private int status;
    private List<RemotePayloadDTO> payload;
}
