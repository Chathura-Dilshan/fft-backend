package com.fft.farm.service;

import com.fft.farm.entity.BodyMassIndex;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BodyMassIndexService {
    ResponseEntity createBodyMassIndex(BodyMassIndex bodyMassIndex);

    ResponseEntity updateBodyMassIndex(BodyMassIndex bodyMassIndex);

    ResponseEntity<BodyMassIndex> deleteBodyMassIndex(Integer bodyMassIndexSeq);

    List<BodyMassIndex> findAllBodyMassIndexs(Integer statusSeq);
}
