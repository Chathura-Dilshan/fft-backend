package com.fft.farm.controller;

import com.fft.farm.entity.Food;
import com.fft.farm.service.FoodService;
import com.fft.farm.util.MasterDataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("foods")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('createFood')")
    public ResponseEntity createFood(@RequestBody Food food) {
        return this.foodService.createFood(food);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('login','food')")
    public ResponseEntity updateFood(@RequestBody Food food) {
        return this.foodService.updateFood(food);
    }

    @DeleteMapping("{foodSeq}")
    @PreAuthorize("hasAnyRole('login','food')")
    public ResponseEntity<Food> deleteFood(@PathVariable("foodSeq") Integer foodSeq) {
        return this.foodService.deleteFood(foodSeq);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('login','food')")
    public List<Food> findAllFoods() {
        return this.foodService.findAllFoods(MasterDataStatus.APPROVED.getStatusSeq());
    }

//    @GetMapping("{foodSeq}")
//    @PreAuthorize("hasRole('ROLE_config@food_VIEW')")
//    public Food findFoodByFoodSeq(@PathVariable("foodSeq") Integer foodSeq) {
//        Optional<Food> food = this.foodRepository.findById(foodSeq);
//        return food.orElse(null);
//    }
}
