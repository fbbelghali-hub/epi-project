package com.example.epi.service;

import com.example.epi.dto.EpiRequestDTO;
import java.util.List;
public interface PurchaseService {

    List<EpiRequestDTO> getValidatedRequests();

    void markAsPurchased(Long requestId);

    List<EpiRequestDTO> getPurchaseHistory();

}
