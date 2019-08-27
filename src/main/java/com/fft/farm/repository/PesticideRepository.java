package com.fft.farm.repository;

import com.fft.farm.entity.Pesticide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PesticideRepository extends JpaRepository<Pesticide, Integer> {
    Optional<Pesticide> findByPesticideName(String pesticideName);

    List<Pesticide> findByStatus(Integer statusSeq);
}
