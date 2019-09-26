package com.fft.farm.repository;

import com.fft.farm.entity.Farm;
import com.fft.farm.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FarmRepository extends JpaRepository<Farm, Integer> {
    Optional<Farm> findByFarmName(String farmName);

    List<Farm> findByStatus(Integer statusSeq);

//    List<Food> findByUserSeqAndStatus(Integer userSeq, Integer statusSeq);

    List<Farm> findByStatusAndUserSeq(Integer statusSeq, Integer userSeq);

    Farm findByStatusAndFarmSeq(Integer statusSeq, Integer farmSeq);
}
