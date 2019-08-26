package com.fft.farm.controller;

import com.fft.farm.entity.Pesticide;
import com.fft.farm.service.PesticideService;
import com.fft.farm.util.MasterDataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pesticides")
public class PesticideController {

    private final PesticideService pesticideService;

    @Autowired
    public PesticideController(PesticideService pesticideService) {
        this.pesticideService = pesticideService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('createPesticide')")
    public ResponseEntity createPesticide(@RequestBody Pesticide pesticide) {
        return this.pesticideService.createPesticide(pesticide);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('login','pesticide')")
    public ResponseEntity updatePesticide(@RequestBody Pesticide pesticide) {
        return this.pesticideService.updatePesticide(pesticide);
    }

    @DeleteMapping("{pesticideSeq}")
    @PreAuthorize("hasAnyRole('login','pesticide')")
    public ResponseEntity<Pesticide> deletePesticide(@PathVariable("pesticideSeq") Integer pesticideSeq) {
        return this.pesticideService.deletePesticide(pesticideSeq);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('login','pesticide')")
    public List<Pesticide> findAllPesticides() {
        return this.pesticideService.findAllPesticides(MasterDataStatus.APPROVED.getStatusSeq());
    }

//    @GetMapping("{pesticideSeq}")
//    @PreAuthorize("hasRole('ROLE_config@pesticide_VIEW')")
//    public Pesticide findPesticideByPesticideSeq(@PathVariable("pesticideSeq") Integer pesticideSeq) {
//        Optional<Pesticide> pesticide = this.pesticideRepository.findById(pesticideSeq);
//        return pesticide.orElse(null);
//    }
}
