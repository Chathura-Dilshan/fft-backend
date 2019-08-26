package com.fft.farm.service;

import com.fft.farm.entity.Fertilizer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FertilizerService {
    ResponseEntity createFertilizer(Fertilizer fertilizer);

    ResponseEntity updateFertilizer(Fertilizer fertilizer);

    ResponseEntity<Fertilizer> deleteFertilizer(Integer fertilizerSeq);

    List<Fertilizer> findAllFertilizers(Integer statusSeq);
}
