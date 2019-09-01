//package com.fft.farm.serviceImpl;
//
//import com.fft.farm.entity.Farm;
//import com.fft.farm.repository.FarmRepository;
//import com.fft.farm.service.FarmService;
//import com.fft.farm.util.MasterDataStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//@Service
//public class FarmServiceImpl implements FarmService {
//
////    private final FarmRepository farmRepository;
////
////    @Autowired
////    public FarmServiceImpl(FarmRepository farmRepository) {
////        this.farmRepository = farmRepository;
////    }
////
////
////    @Override
////    public ResponseEntity createFarm(Farm farm) {
////        ResponseEntity responseEntity;
////        Optional<Farm> existFarmName = this.farmRepository.findByFarmName(farm.getFarmName());
////        Set<ConstraintViolation<Farm>> errors = Validation.buildDefaultValidatorFactory().
////                getValidator().validate(farm);
////        if (errors.size() > 0) {
//////            String errorMessage = ErrorMessageCreator.errorsInRow((HashSet<?>) errors);
////            responseEntity = new ResponseEntity<>("Farm already exist", HttpStatus.BAD_REQUEST);
////        } else {
////
////            if (existFarmName.isPresent()) {
////                responseEntity = new ResponseEntity<>("Farm already exist", HttpStatus.BAD_REQUEST);
////
////            } else {
////                String username = SecurityContextHolder.getContext().getAuthentication().getName();
////                farm.setCreatedBy(username);
////                farm.setCreatedDate(new Date());
////                farm.setLastModifiedBy(username);
////                farm.setLastModifiedDate(new Date());
////                farm.setStatus(MasterDataStatus.APPROVED.getStatusSeq());
////                farm.setFarmSeq(null);
////                this.farmRepository.save(farm);
////                responseEntity = new ResponseEntity<>(farm, HttpStatus.CREATED);
////            }
////
////
////        }
////        return responseEntity;
////    }
////
////    @Override
////    public ResponseEntity updateFarm(Farm farm) {
////        Optional<Farm> dbFarm = this.farmRepository.findById(farm.getFarmSeq());
////        ResponseEntity responseEntity;
////        if (dbFarm.isPresent()) {
////            if (dbFarm.get().equals(farm)) {
////                responseEntity = new ResponseEntity<>(farm, HttpStatus.NOT_MODIFIED);
////            } else {
////                Set<ConstraintViolation<Farm>> errors = Validation.buildDefaultValidatorFactory().
////                        getValidator().validate(farm);
////                if (errors.size() > 0) {
//////                    String errorMessage = ErrorMessageCreator.errorsInRow((HashSet<?>) errors);
////                    responseEntity = new ResponseEntity<>("Farm already exist", HttpStatus.BAD_REQUEST);
////                } else {
////                    farm = this.farmRepository.save(farm);
////                    responseEntity = new ResponseEntity<>(farm, HttpStatus.CREATED);
////                }
////            }
////        } else {
////            responseEntity = new ResponseEntity<>(farm, HttpStatus.NOT_FOUND);
////        }
////        return responseEntity;
////    }
////
////    @Override
////    public ResponseEntity<Farm> deleteFarm(Integer farmSeq) {
////        Optional<Farm> dbFarm = this.farmRepository.findById(farmSeq);
////        ResponseEntity<Farm> responseEntity;
////        if (dbFarm.isPresent()) {
////            Farm farm = dbFarm.get();
////            farm.setStatus(MasterDataStatus.DELETED.getStatusSeq());
////            this.farmRepository.save(farm);
////            responseEntity = new ResponseEntity<>(HttpStatus.OK);
////        } else {
////            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
////        return responseEntity;
////    }
////
////    @Override
////    public List<Farm> findAllFarms(Integer statusSeq) {
////        return this.farmRepository.findByStatus(statusSeq);
////    }
//}
