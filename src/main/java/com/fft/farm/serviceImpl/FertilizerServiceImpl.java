package com.fft.farm.serviceImpl;

import com.fft.farm.entity.Fertilizer;
import com.fft.farm.repository.FertilizerRepository;
import com.fft.farm.service.FertilizerService;
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
public class FertilizerServiceImpl implements FertilizerService {
    private final FertilizerRepository fertilizerRepository;

    @Autowired
    public FertilizerServiceImpl(FertilizerRepository fertilizerRepository) {
        this.fertilizerRepository = fertilizerRepository;
    }


    @Override
    public ResponseEntity createFertilizer(Fertilizer fertilizer) {
        ResponseEntity responseEntity;
        Optional<Fertilizer> existFertilizerName = this.fertilizerRepository.findByFertilizerName(fertilizer.getFertilizerName());
        Set<ConstraintViolation<Fertilizer>> errors = Validation.buildDefaultValidatorFactory().
                getValidator().validate(fertilizer);
        if (errors.size() > 0) {
//            String errorMessage = ErrorMessageCreator.errorsInRow((HashSet<?>) errors);
            responseEntity = new ResponseEntity<>("Fertilizer already exist", HttpStatus.BAD_REQUEST);
        } else {

            if (existFertilizerName.isPresent()) {
                responseEntity = new ResponseEntity<>("Fertilizer already exist", HttpStatus.BAD_REQUEST);

            } else {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                fertilizer.setCreatedBy(username);
                fertilizer.setCreatedDate(new Date());
                fertilizer.setLastModifiedBy(username);
                fertilizer.setLastModifiedDate(new Date());
                fertilizer.setStatus(MasterDataStatus.APPROVED.getStatusSeq());
                fertilizer.setFertilizerSeq(null);
                this.fertilizerRepository.save(fertilizer);
                responseEntity = new ResponseEntity<>(fertilizer, HttpStatus.CREATED);
            }


        }
        return responseEntity;
    }

    @Override
    public ResponseEntity updateFertilizer(Fertilizer fertilizer) {
        Optional<Fertilizer> dbFertilizer = this.fertilizerRepository.findById(fertilizer.getFertilizerSeq());
        ResponseEntity responseEntity;
        if (dbFertilizer.isPresent()) {
            if (dbFertilizer.get().equals(fertilizer)) {
                responseEntity = new ResponseEntity<>(fertilizer, HttpStatus.NOT_MODIFIED);
            } else {
                Set<ConstraintViolation<Fertilizer>> errors = Validation.buildDefaultValidatorFactory().
                        getValidator().validate(fertilizer);
                if (errors.size() > 0) {
//                    String errorMessage = ErrorMessageCreator.errorsInRow((HashSet<?>) errors);
                    responseEntity = new ResponseEntity<>("Fertilizer already exist", HttpStatus.BAD_REQUEST);
                } else {
                    fertilizer = this.fertilizerRepository.save(fertilizer);
                    responseEntity = new ResponseEntity<>(fertilizer, HttpStatus.CREATED);
                }
            }
        } else {
            responseEntity = new ResponseEntity<>(fertilizer, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Fertilizer> deleteFertilizer(Integer fertilizerSeq) {
        Optional<Fertilizer> dbFertilizer = this.fertilizerRepository.findById(fertilizerSeq);
        ResponseEntity<Fertilizer> responseEntity;
        if (dbFertilizer.isPresent()) {
            Fertilizer fertilizer = dbFertilizer.get();
            fertilizer.setStatus(MasterDataStatus.DELETED.getStatusSeq());
            this.fertilizerRepository.save(fertilizer);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public List<Fertilizer> findAllFertilizers(Integer statusSeq) {
        return this.fertilizerRepository.findByStatus(statusSeq);
    }
}
