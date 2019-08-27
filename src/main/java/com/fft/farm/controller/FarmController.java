package com.fft.farm.controller;

import com.fft.farm.entity.Farm;
import com.fft.farm.service.FarmService;
import com.fft.farm.util.MasterDataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bodyMassIndexes")
public class FarmController {

    private final FarmService farmService;

    @Autowired
    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('createFarm')")
    public ResponseEntity createFarm(@RequestBody Farm farm) {
        return this.farmService.createFarm(farm);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('login','farm')")
    public ResponseEntity updateFarm(@RequestBody Farm farm) {
        return this.farmService.updateFarm(farm);
    }

    @DeleteMapping("{farmSeq}")
    @PreAuthorize("hasAnyRole('login','farm')")
    public ResponseEntity<Farm> deleteFarm(@PathVariable("farmSeq") Integer farmSeq) {
        return this.farmService.deleteFarm(farmSeq);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('login','farm')")
    public List<Farm> findAllFarms() {
        return this.farmService.findAllFarms(MasterDataStatus.APPROVED.getStatusSeq());
    }
}
