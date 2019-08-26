package com.fft.farm.serviceImpl;

import com.fft.farm.entity.Food;
import com.fft.farm.repository.FoodRepository;
import com.fft.farm.service.FoodService;
import com.fft.farm.util.MasterDataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }


    @Override
    public ResponseEntity createFood(Food food) {
        ResponseEntity responseEntity;
        Optional<Food> existFoodName = this.foodRepository.findByFoodName(food.getFoodName());
        Set<ConstraintViolation<Food>> errors = Validation.buildDefaultValidatorFactory().
                getValidator().validate(food);
        if (errors.size() > 0) {
//            String errorMessage = ErrorMessageCreator.errorsInRow((HashSet<?>) errors);
            responseEntity = new ResponseEntity<>("Food already exist", HttpStatus.BAD_REQUEST);
        } else {

            if (existFoodName.isPresent()) {
                responseEntity = new ResponseEntity<>("Food already exist", HttpStatus.BAD_REQUEST);

            } else {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                food.setCreatedBy(username);
                food.setCreatedDate(new Date());
                food.setLastModifiedBy(username);
                food.setLastModifiedDate(new Date());
                food.setStatus(MasterDataStatus.APPROVED.getStatusSeq());
                food.setFoodSeq(null);
                this.foodRepository.save(food);
                responseEntity = new ResponseEntity<>(food, HttpStatus.CREATED);
            }


        }
        return responseEntity;
    }

    @Override
    public ResponseEntity updateFood(Food food) {
        Optional<Food> dbFood = this.foodRepository.findById(food.getFoodSeq());
        ResponseEntity responseEntity;
        if (dbFood.isPresent()) {
            if (dbFood.get().equals(food)) {
                responseEntity = new ResponseEntity<>(food, HttpStatus.NOT_MODIFIED);
            } else {
                Set<ConstraintViolation<Food>> errors = Validation.buildDefaultValidatorFactory().
                        getValidator().validate(food);
                if (errors.size() > 0) {
//                    String errorMessage = ErrorMessageCreator.errorsInRow((HashSet<?>) errors);
                    responseEntity = new ResponseEntity<>("Food already exist", HttpStatus.BAD_REQUEST);
                } else {
                    food = this.foodRepository.save(food);
                    responseEntity = new ResponseEntity<>(food, HttpStatus.CREATED);
                }
            }
        } else {
            responseEntity = new ResponseEntity<>(food, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Food> deleteFood(Integer foodSeq) {
        Optional<Food> dbFood = this.foodRepository.findById(foodSeq);
        ResponseEntity<Food> responseEntity;
        if (dbFood.isPresent()) {
            Food food = dbFood.get();
            food.setStatus(MasterDataStatus.DELETED.getStatusSeq());
            this.foodRepository.save(food);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public List<Food> findAllFoods(Integer statusSeq) {
        return this.foodRepository.findByStatus(statusSeq);
    }
}
