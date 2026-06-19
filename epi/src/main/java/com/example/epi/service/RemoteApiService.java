package com.example.epi.service;

import com.example.epi.dto.RemotePayloadDTO;
import com.example.epi.dto.RemoteResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class RemoteApiService {
    private static final Logger log = LoggerFactory.getLogger(EpiRequestServiceImpl.class);
    private final RestTemplate restTemplate;

    public List<RemotePayloadDTO> fetchData() {

        log.info("Calling remote API...");

        String url = "http://localhost:8000/test/rhna";

        RemoteResponseDTO response =
                restTemplate.getForObject(url, RemoteResponseDTO.class);

        if (response == null || response.getPayload() == null) {
            log.warn("Remote API returned null or empty payload");
            return new ArrayList<>();
        }

        log.info("Remote API returned {} records",
                response.getPayload().size());

        return response.getPayload();
    }
}
