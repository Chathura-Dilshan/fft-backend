package com.fft.farm.controller;

import com.fft.farm.entity.BodyMassIndex;
import com.fft.farm.service.BodyMassIndexService;
import com.fft.farm.util.MasterDataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("bodyMassIndexes")
public class BodyMassIndexController {

    private final BodyMassIndexService bodyMassIndexService;

    @Autowired
    public BodyMassIndexController(BodyMassIndexService bodyMassIndexService) {
        this.bodyMassIndexService = bodyMassIndexService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('createBodyMassIndex')")
    public ResponseEntity createBodyMassIndex(@RequestBody BodyMassIndex bodyMassIndex) {
        return this.bodyMassIndexService.createBodyMassIndex(bodyMassIndex);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('login','bodyMassIndex')")
    public ResponseEntity updateBodyMassIndex(@RequestBody BodyMassIndex bodyMassIndex) {
        return this.bodyMassIndexService.updateBodyMassIndex(bodyMassIndex);
    }

    @DeleteMapping("{bodyMassIndexSeq}")
    @PreAuthorize("hasAnyRole('login','bodyMassIndex')")
    public ResponseEntity<BodyMassIndex> deleteBodyMassIndex(@PathVariable("bodyMassIndexSeq") Integer bodyMassIndexSeq) {
        return this.bodyMassIndexService.deleteBodyMassIndex(bodyMassIndexSeq);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('login','bodyMassIndex')")
    public List<BodyMassIndex> findAllBodyMassIndexs() {
        return this.bodyMassIndexService.findAllBodyMassIndexs(MasterDataStatus.APPROVED.getStatusSeq());
    }

//    @GetMapping("{bodyMassIndexSeq}")
//    @PreAuthorize("hasRole('ROLE_config@bodyMassIndex_VIEW')")
//    public BodyMassIndex findBodyMassIndexByBodyMassIndexSeq(@PathVariable("bodyMassIndexSeq") Integer bodyMassIndexSeq) {
//        Optional<BodyMassIndex> bodyMassIndex = this.bodyMassIndexRepository.findById(bodyMassIndexSeq);
//        return bodyMassIndex.orElse(null);
//    }
}
