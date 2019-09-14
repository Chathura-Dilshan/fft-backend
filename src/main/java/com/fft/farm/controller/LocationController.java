package com.fft.farm.controller;

import com.fft.farm.entity.Food;
import com.fft.farm.entity.Location;
import com.fft.farm.service.FoodService;
import com.fft.farm.service.LocationService;
import com.fft.farm.util.MasterDataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("locations")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }


    @PostMapping
    @PreAuthorize("hasAnyRole('login','createLocation')")
    public ResponseEntity createLocation(@RequestBody Location location) {
        return this.locationService.createLocation(location);
    }

    @GetMapping("/findAllLocationsByUserSeq")
    @PreAuthorize("hasAnyRole('login','location')")
    public List<Location> findAllLocationsByUserSeq() {
        return this.locationService.findAllLocationsByUserSeq(MasterDataStatus.APPROVED.getStatusSeq());
    }

}
