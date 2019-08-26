package com.fft.farm.service;

import com.fft.farm.entity.Food;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FoodService {
    ResponseEntity createFood(Food food);

    ResponseEntity updateFood(Food food);

    ResponseEntity<Food> deleteFood(Integer foodSeq);

    List<Food> findAllFoods(Integer statusSeq);
}
