package com.example.epi.service;

import com.example.epi.dto.EpiRequestDTO;
import com.example.epi.entity.EpiRequest;
import com.example.epi.enums.RequestStatus;
import com.example.epi.Mapper.EpiRequestMapper;
import com.example.epi.repository.EPIRequestRepository;
import com.example.epi.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final EPIRequestRepository requestRepository;
    private final EpiRequestMapper mapper;

    private static final Logger log = LoggerFactory.getLogger(PurchaseServiceImpl.class);
    // VALIDATED REQUESTS
    @Override
    public List<EpiRequestDTO> getValidatedRequests() {

        log.info("GET validated requests");

        List<EpiRequest> requests = requestRepository.findAll();

        List<EpiRequestDTO> result = new ArrayList<>();

        for (EpiRequest request : requests) {

            if (request.getStatus() == RequestStatus.VALIDEE||request.getStatus() == RequestStatus.ACHAT_EFFECTUE) {
                result.add(mapper.toDTO(request));
            }
        }

        log.info("Found {} validated requests", result.size());

        return result;
    }

    // MARK AS PURCHASED
    @Override
    public void markAsPurchased(Long requestId) {

        log.info("MARK AS PURCHASED id={}", requestId);

        EpiRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> {
                    log.error("Request not found id={}", requestId);
                    return new RuntimeException("Request not found");
                });

        request.setStatus(RequestStatus.ACHAT_EFFECTUE);
        request.setPurchaseDate(LocalDate.now());

        requestRepository.save(request);

        log.info("Request {} marked as purchased", requestId);
    }

    // PURCHASE HISTORY
    @Override
    public List<EpiRequestDTO> getPurchaseHistory() {
        List<EpiRequest> requests = requestRepository.findAll();

        List<EpiRequestDTO> result = new ArrayList<>();

        for (EpiRequest request : requests) {

            if (request.getStatus() == RequestStatus.ACHAT_EFFECTUE) {
                result.add(mapper.toDTO(request));
            }
        }

        return result;
    }
}
