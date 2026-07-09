package com.example.epi.service;


import com.example.epi.dto.*;
import com.example.epi.entity.EpiRequest;
import com.example.epi.dto.SearchRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EpiRequestService {
    EpiRequestDTO createRequest(Long userId, CreateEpiRequestDTO dto);

    EpiRequestDTO getRequestById(Long id);

    List<EpiRequestDTO> getMyRequests(Long userId);

    List<EpiRequestDTO> getAllRequests();

    EpiRequestDTO updateRequest(Long id, UpdateEpiRequestDTO dto);

    void cancelRequest(Long id);

    void validateRequest(Long id);

    void rejectRequest(Long id, String comment);

    List<EpiRequestDTO> getRequestsForDirecteur(Long directeurId);
    List<EpiRequestDTO> searchRequests(
            SearchRequestDTO dto,
            String role,
            Long employeeId,
            Long projetId);
}
