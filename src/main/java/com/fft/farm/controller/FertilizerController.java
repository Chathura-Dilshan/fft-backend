package com.fft.farm.controller;

import com.fft.farm.entity.Fertilizer;
import com.fft.farm.service.FertilizerService;
import com.fft.farm.util.MasterDataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fertilizers")
public class FertilizerController {
    private final FertilizerService fertilizerService;
    
    @Autowired
    public FertilizerController(FertilizerService fertilizerService) {
        this.fertilizerService = fertilizerService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('createFertilizer')")
    public ResponseEntity createFertilizer(@RequestBody Fertilizer fertilizer) {
        return this.fertilizerService.createFertilizer(fertilizer);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('login','fertilizer')")
    public ResponseEntity updateFertilizer(@RequestBody Fertilizer fertilizer) {
        return this.fertilizerService.updateFertilizer(fertilizer);
    }

    @DeleteMapping("{fertilizerSeq}")
    @PreAuthorize("hasAnyRole('login','fertilizer')")
    public ResponseEntity<Fertilizer> deleteFertilizer(@PathVariable("fertilizerSeq") Integer fertilizerSeq) {
        return this.fertilizerService.deleteFertilizer(fertilizerSeq);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('login','fertilizer')")
    public List<Fertilizer> findAllFertilizers() {
        return this.fertilizerService.findAllFertilizers(MasterDataStatus.APPROVED.getStatusSeq());
    }

//    @GetMapping("{fertilizerSeq}")
//    @PreAuthorize("hasRole('ROLE_config@fertilizer_VIEW')")
//    public Fertilizer findFertilizerByFertilizerSeq(@PathVariable("fertilizerSeq") Integer fertilizerSeq) {
//        Optional<Fertilizer> fertilizer = this.fertilizerRepository.findById(fertilizerSeq);
//        return fertilizer.orElse(null);
//    }
}
