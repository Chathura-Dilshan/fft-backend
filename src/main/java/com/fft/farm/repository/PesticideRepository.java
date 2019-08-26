package com.fft.farm.repository;

import com.fft.farm.entity.Pesticide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PesticideRepository extends JpaRepository<Pesticide, Integer> {
    Optional<Pesticide> findByPesticideName(String pesticideName);

    List<Pesticide> findByStatus(Integer statusSeq);
}
