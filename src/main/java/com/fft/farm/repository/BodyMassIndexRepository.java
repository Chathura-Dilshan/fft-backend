package com.fft.farm.repository;

import com.fft.farm.entity.BodyMassIndex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BodyMassIndexRepository extends JpaRepository<BodyMassIndex, Integer> {
    Optional<BodyMassIndex> findByUserSeq(Integer userSeq);

    List<BodyMassIndex> findByStatus(Integer statusSeq);
}
