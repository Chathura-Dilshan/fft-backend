package com.fft.farm.repository;

import com.fft.farm.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FertilizerRepository extends JpaRepository<Fertilizer, Integer> {
    Optional<Fertilizer> findByFertilizerName(String fertilizerName);

    List<Fertilizer> findByStatus(Integer statusSeq);
}
