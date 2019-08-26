package com.fft.farm.service;

import com.fft.farm.entity.Pesticide;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PesticideService {
    ResponseEntity createPesticide(Pesticide pesticide);

    ResponseEntity updatePesticide(Pesticide pesticide);

    ResponseEntity<Pesticide> deletePesticide(Integer pesticideSeq);

    List<Pesticide> findAllPesticides(Integer statusSeq);
}
