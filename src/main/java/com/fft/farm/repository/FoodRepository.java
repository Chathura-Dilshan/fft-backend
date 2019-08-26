package com.fft.farm.repository;

import com.fft.farm.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
    List<Food> findByStatus(Integer statusSeq);

    Optional<Food> findByFoodName(String foodName);
}
