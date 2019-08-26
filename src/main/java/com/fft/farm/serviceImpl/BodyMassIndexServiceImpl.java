package com.fft.farm.serviceImpl;

import com.fft.farm.entity.BodyMassIndex;
import com.fft.farm.repository.BodyMassIndexRepository;
import com.fft.farm.service.BodyMassIndexService;
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
public class BodyMassIndexServiceImpl implements BodyMassIndexService {
    private final BodyMassIndexRepository bodyMassIndexRepository;

    @Autowired
    public BodyMassIndexServiceImpl(BodyMassIndexRepository bodyMassIndexRepository) {
        this.bodyMassIndexRepository = bodyMassIndexRepository;
    }


    @Override
    public ResponseEntity createBodyMassIndex(BodyMassIndex bodyMassIndex) {
        ResponseEntity responseEntity;
        Optional<BodyMassIndex> existBodyMassIndexName = this.bodyMassIndexRepository.findByUserSeq(bodyMassIndex.getUserSeq());
        Set<ConstraintViolation<BodyMassIndex>> errors = Validation.buildDefaultValidatorFactory().
                getValidator().validate(bodyMassIndex);
        if (errors.size() > 0) {
//            String errorMessage = ErrorMessageCreator.errorsInRow((HashSet<?>) errors);
            responseEntity = new ResponseEntity<>("BodyMassIndex already exist", HttpStatus.BAD_REQUEST);
        } else {

            if (existBodyMassIndexName.isPresent()) {
                responseEntity = new ResponseEntity<>("BodyMassIndex already exist", HttpStatus.BAD_REQUEST);

            } else {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                bodyMassIndex.setCreatedBy(username);
                bodyMassIndex.setCreatedDate(new Date());
                bodyMassIndex.setLastModifiedBy(username);
                bodyMassIndex.setLastModifiedDate(new Date());
                bodyMassIndex.setStatus(MasterDataStatus.APPROVED.getStatusSeq());
                bodyMassIndex.setBodyMassIndexSeq(null);
                this.bodyMassIndexRepository.save(bodyMassIndex);
                responseEntity = new ResponseEntity<>(bodyMassIndex, HttpStatus.CREATED);
            }


        }
        return responseEntity;
    }

    @Override
    public ResponseEntity updateBodyMassIndex(BodyMassIndex bodyMassIndex) {
        Optional<BodyMassIndex> dbBodyMassIndex = this.bodyMassIndexRepository.findById(bodyMassIndex.getBodyMassIndexSeq());
        ResponseEntity responseEntity;
        if (dbBodyMassIndex.isPresent()) {
            if (dbBodyMassIndex.get().equals(bodyMassIndex)) {
                responseEntity = new ResponseEntity<>(bodyMassIndex, HttpStatus.NOT_MODIFIED);
            } else {
                Set<ConstraintViolation<BodyMassIndex>> errors = Validation.buildDefaultValidatorFactory().
                        getValidator().validate(bodyMassIndex);
                if (errors.size() > 0) {
//                    String errorMessage = ErrorMessageCreator.errorsInRow((HashSet<?>) errors);
                    responseEntity = new ResponseEntity<>("BodyMassIndex already exist", HttpStatus.BAD_REQUEST);
                } else {
                    bodyMassIndex = this.bodyMassIndexRepository.save(bodyMassIndex);
                    responseEntity = new ResponseEntity<>(bodyMassIndex, HttpStatus.CREATED);
                }
            }
        } else {
            responseEntity = new ResponseEntity<>(bodyMassIndex, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<BodyMassIndex> deleteBodyMassIndex(Integer bodyMassIndexSeq) {
        Optional<BodyMassIndex> dbBodyMassIndex = this.bodyMassIndexRepository.findById(bodyMassIndexSeq);
        ResponseEntity<BodyMassIndex> responseEntity;
        if (dbBodyMassIndex.isPresent()) {
            BodyMassIndex bodyMassIndex = dbBodyMassIndex.get();
            bodyMassIndex.setStatus(MasterDataStatus.DELETED.getStatusSeq());
            this.bodyMassIndexRepository.save(bodyMassIndex);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public List<BodyMassIndex> findAllBodyMassIndexs(Integer statusSeq) {
        return this.bodyMassIndexRepository.findByStatus(statusSeq);
    }
}
