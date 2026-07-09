package com.example.epi.service;

import com.example.epi.dto.*;
import com.example.epi.entity.*;
import com.example.epi.enums.RequestStatus;
import com.example.epi.Mapper.EpiRequestMapper;
import com.example.epi.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.epi.criteria.EpiRequestCriteria;
import com.example.epi.dto.SearchRequestDTO;

@Service
@RequiredArgsConstructor

public class EpiRequestServiceImpl implements EpiRequestService {

    private final EPIRequestRepository requestRepository;
    private final UserRepository userRepository;
    private final EpiRespository epiRepository;
    private final EpiRequestMapper mapper;

    private static final Logger log = LoggerFactory.getLogger(EpiRequestServiceImpl.class);
    // CREATE (Collaborateur)
    @Override
    public EpiRequestDTO createRequest(Long userId, CreateEpiRequestDTO dto) {

        log.info(" START CREATE REQUEST - userId={}, epiId={}", userId, dto.getEpiId());

        try {

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> {
                        log.error(" User not found - id={}", userId);
                        return new RuntimeException("User not found");
                    });

            Epi epi = epiRepository.findById(dto.getEpiId())
                    .orElseThrow(() -> {
                        log.error(" EPI not found - id={}", dto.getEpiId());
                        return new RuntimeException("EPI not found");
                    });

            EpiRequest request = mapper.toEntity(dto);
            request.setEmployee(user);
            request.setEpi(epi);
            request.setStatus(RequestStatus.EN_COURS);
            request.setRequestDate(LocalDate.now());

            EpiRequest saved = requestRepository.save(request);

            log.info("SUCCESS CREATE REQUEST - requestId={}", saved.getId());

            return mapper.toDTO(saved);

        } catch (Exception e) {

            log.error(" ERROR CREATE REQUEST - userId={}, epiId={}", userId, dto.getEpiId(), e);
            throw e;
        }
    }

    // GET BY ID
    @Override
    public EpiRequestDTO getRequestById(Long id) {

        EpiRequest request = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        return mapper.toDTO(request);
    }

    // GET MY REQUESTS (Collaborateur)
    @Override
    public List<EpiRequestDTO> getMyRequests(Long userId) {
        log.info(" GET MY REQUESTS - userId={}", userId);
        List<EpiRequest> requests = requestRepository.findAll();
        List<EpiRequestDTO> result = new ArrayList<>();

        for (EpiRequest request : requests) {
            if (request.getEmployee().getId().equals(userId)) {
                result.add(mapper.toDTO(request));
            }
        }

        return result;
    }

    // GET ALL (admin)
    @Override
    public List<EpiRequestDTO> getAllRequests() {
        log.info("GET ALL REQUESTS");
        List<EpiRequest> requests = requestRepository.findAll();
        List<EpiRequestDTO> result = new ArrayList<>();

        for (EpiRequest request : requests) {
            result.add(mapper.toDTO(request));
        }

        return result;
    }

    // UPDATE
    @Override
    public EpiRequestDTO updateRequest(Long id, UpdateEpiRequestDTO dto) {

        EpiRequest request = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.getStatus() != RequestStatus.EN_COURS) {
            throw new RuntimeException("Cannot update request");
        }
        Epi epi = epiRepository.findById(dto.getEpiId())
                .orElseThrow(() -> new RuntimeException("EPI not found"));

        request.setEpi(epi);
        request.setRequestQuantity(dto.getRequestQuantity());
        request.setReason(dto.getReason());
        request.setSize(dto.getSize());

        return mapper.toDTO(requestRepository.save(request));
    }

    // CANCEL
    @Override
    public void cancelRequest(Long id) {

        log.info(" START CANCEL REQUEST - id={}", id);

        try {

            EpiRequest request = requestRepository.findById(id)
                    .orElseThrow(() -> {
                        log.error(" Request not found - id={}", id);
                        return new RuntimeException("Request not found");
                    });

            request.setStatus(RequestStatus.ANNULEE);

            requestRepository.save(request);

            log.info(" SUCCESS CANCEL REQUEST - id={}", id);

        } catch (Exception e) {

            log.error(" ERROR CANCEL REQUEST - id={}", id, e);
            throw e;
        }
    }



    // VALIDATE
    @Override
    public void validateRequest(Long id) {

        log.info(" START VALIDATE REQUEST - id={}", id);

        try {

            EpiRequest request = requestRepository.findById(id)
                    .orElseThrow(() -> {
                        log.error(" Request not found - id={}", id);
                        return new RuntimeException("Request not found");
                    });

            request.setStatus(RequestStatus.VALIDEE);
            requestRepository.save(request);

            log.info("SUCCESS VALIDATE REQUEST - id={}", id);

        } catch (Exception e) {

            log.error(" ERROR VALIDATE REQUEST - id={}", id, e);
            throw e;
        }
    }

    // REJECT
    @Override
    public void rejectRequest(Long id, String comment) {

        log.info(" START REJECT REQUEST - id={}, reason={}", id, comment);

        try {

            EpiRequest request = requestRepository.findById(id)
                    .orElseThrow(() -> {
                        log.error(" Request not found - id={}", id);
                        return new RuntimeException("Request not found");
                    });

            request.setStatus(RequestStatus.REFUSEE);
            request.setReason(comment);

            requestRepository.save(request);

            log.info(" SUCCESS REJECT REQUEST - id={}", id);

        } catch (Exception e) {

            log.error(" ERROR REJECT REQUEST - id={}", id, e);
            throw e;
        }
    }

    // DIRECTEUR
    @Override
    public List<EpiRequestDTO> getRequestsForDirecteur(Long directeurId) {

        log.info(" START GET REQUESTS FOR DIRECTEUR - id={}", directeurId);

        try {

            User directeur = userRepository.findById(directeurId)
                    .orElseThrow(() -> {
                        log.error(" Directeur not found - id={}", directeurId);
                        return new RuntimeException("Directeur not found");
                    });

            Long projetId = directeur.getProjet().getId();

            List<EpiRequest> requests = requestRepository.findAll();

            List<EpiRequestDTO> result = new ArrayList<>();

            for (EpiRequest request : requests) {

                if (request.getEmployee().getProjet().getId().equals(projetId)) {
                    result.add(mapper.toDTO(request));
                }
            }

            log.info(" SUCCESS DIRECTEUR REQUESTS - count={}", result.size());

            return result;

        } catch (Exception e) {

            log.error(" ERROR GET DIRECTEUR REQUESTS - id={}", directeurId, e);
            throw e;
        }
    }
    @Override
    public List<EpiRequestDTO> searchRequests(
            SearchRequestDTO dto,
            String role,
            Long employeeId,
            Long projetId) {

        if ("DIRECTEUR".equals(role)) {

            User directeur = userRepository.findById(projetId)
                    .orElseThrow(() ->
                            new RuntimeException("Directeur introuvable"));

            projetId = directeur.getProjet().getId();
        }

        List<EpiRequest> requests = requestRepository.findAll(
                EpiRequestCriteria.search(
                        dto,
                        role,
                        employeeId,
                        projetId
                )
        );

        return requests.stream()
                .map(mapper::toDTO)
                .toList();
    }
}