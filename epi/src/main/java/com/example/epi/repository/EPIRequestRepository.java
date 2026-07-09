package com.example.epi.repository;

import com.example.epi.entity.EpiRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EPIRequestRepository extends JpaRepository<EpiRequest, Long>,JpaSpecificationExecutor<EpiRequest>{
    @Query("SELECT r FROM EpiRequest r " +
            "JOIN r.employee u " +
            "WHERE u.projet.id = (SELECT d.projet.id FROM User d WHERE d.id = :directeurId)")
    List<EpiRequest> findRequestsForDirecteur(@Param("directeurId") Long directeurId);

    List<EpiRequest> findByEmployeeId(Long employeeId);
}
