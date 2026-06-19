package com.example.epi.controller;

import com.example.epi.dto.CreateEpiRequestDTO;
import com.example.epi.dto.EpiRequestDTO;
import com.example.epi.dto.UpdateEpiRequestDTO;
import com.example.epi.service.EpiRequestService;
import com.example.epi.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor

public class EpiRequestController {

    private final EpiRequestService epiRequestService;
    private final PurchaseService purchaseService;


    //affiche tous les demandes
    @GetMapping("/directeur/{directeurId}")
    public ResponseEntity<List<EpiRequestDTO>> getDemandesParDirecteur(
            @PathVariable Long directeurId) {

        List<EpiRequestDTO> requests =
                epiRequestService.getRequestsForDirecteur(directeurId);

        return ResponseEntity.ok(requests);
    }
    @PostMapping("/create/{userId}")
    public EpiRequestDTO createRequest(@RequestBody CreateEpiRequestDTO dto,
                                       @PathVariable Long userId) {
        return epiRequestService.createRequest(userId, dto);
    }

    @PutMapping("/validate/{id}")
    public void validate(@PathVariable Long id) {
        epiRequestService.validateRequest(id);
    }

    @PutMapping("/reject/{id}")
    public void reject(@PathVariable Long id,
                       @RequestParam String reason) {
        epiRequestService.rejectRequest(id, reason);
    }

    @GetMapping("/validated")
    public List<EpiRequestDTO> getValidatedRequests() {
        return purchaseService.getValidatedRequests();    }

    @PutMapping("/purchase/{id}")
    public void markAsPurchased(@PathVariable Long id) {
        purchaseService.markAsPurchased(id);
    }

    @GetMapping("/user/{userId}")
    public List<EpiRequestDTO> getUserRequests(@PathVariable Long userId) {
        return epiRequestService.getMyRequests(userId);
    }

    @GetMapping("/{id}")
    public EpiRequestDTO getRequestById(@PathVariable Long id) {
        return epiRequestService.getRequestById(id);
    }

    @PutMapping("/{id}")
    public EpiRequestDTO updateRequest(@PathVariable Long id,
                                       @RequestBody UpdateEpiRequestDTO dto) {
        return epiRequestService.updateRequest(id, dto);
    }

    @PutMapping("/cancel/{id}")
    public void cancelRequest(@PathVariable Long id) {
        epiRequestService.cancelRequest(id);
    }

    @GetMapping
    public List<EpiRequestDTO> getAllRequests() {
        return epiRequestService.getAllRequests();
    }


}
