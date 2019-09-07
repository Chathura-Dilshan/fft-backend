package com.fft.farm.service;

import com.fft.farm.entity.Farm;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FarmService {
    ResponseEntity createFarm(Farm farm);

    ResponseEntity updateFarm(Farm farm);

    ResponseEntity<Farm> deleteFarm(Integer farmSeq);

    List<Farm> findAllFarms(Integer statusSeq);

    List<Farm> findAllFarmsByUserSeq(Integer statusSeq);
}
