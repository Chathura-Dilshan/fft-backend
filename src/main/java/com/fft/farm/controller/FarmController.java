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
@RequestMapping("farms")
public class FarmController {

    private final FarmService farmService;

    @Autowired
    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('farm')")
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

    @GetMapping("find/{farmSeq}")
    @PreAuthorize("hasAnyRole('login','farm','createTransactionDetails')")
    public Farm getFarmByFarmSeq(@PathVariable("farmSeq") Integer farmSeq) {
        return this.farmService.findByFarmSeqAndStatus(farmSeq,MasterDataStatus.APPROVED.getStatusSeq());
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('login','farm')")
    public List<Farm> findAllFarms() {
        return this.farmService.findAllFarms(MasterDataStatus.APPROVED.getStatusSeq());
    }

    @GetMapping("/findAllFarmsByUserSeq")
    @PreAuthorize("hasAnyRole('login','farm')")
    public List<Farm> findAllFarmsByUserSeq() {
        return this.farmService.findAllFarmsByUserSeq(MasterDataStatus.APPROVED.getStatusSeq());
    }
}
