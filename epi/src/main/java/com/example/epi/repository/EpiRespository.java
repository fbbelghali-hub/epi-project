package com.example.epi.repository;

import com.example.epi.entity.Epi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpiRespository extends JpaRepository<Epi, Long> {
}
