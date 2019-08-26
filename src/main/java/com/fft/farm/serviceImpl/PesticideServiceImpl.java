package com.fft.farm.serviceImpl;

import com.fft.farm.entity.Pesticide;
import com.fft.farm.repository.PesticideRepository;
import com.fft.farm.service.PesticideService;
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
public class PesticideServiceImpl implements PesticideService {

    private final PesticideRepository pesticideRepository;

    @Autowired
    public PesticideServiceImpl(PesticideRepository pesticideRepository) {
        this.pesticideRepository = pesticideRepository;
    }


    @Override
    public ResponseEntity createPesticide(Pesticide pesticide) {
        ResponseEntity responseEntity;
        Optional<Pesticide> existPesticideName = this.pesticideRepository.findByPesticideName(pesticide.getPesticideName());
        Set<ConstraintViolation<Pesticide>> errors = Validation.buildDefaultValidatorFactory().
                getValidator().validate(pesticide);
        if (errors.size() > 0) {
//            String errorMessage = ErrorMessageCreator.errorsInRow((HashSet<?>) errors);
            responseEntity = new ResponseEntity<>("Pesticide already exist", HttpStatus.BAD_REQUEST);
        } else {

            if (existPesticideName.isPresent()) {
                responseEntity = new ResponseEntity<>("Pesticide already exist", HttpStatus.BAD_REQUEST);

            } else {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                pesticide.setCreatedBy(username);
                pesticide.setCreatedDate(new Date());
                pesticide.setLastModifiedBy(username);
                pesticide.setLastModifiedDate(new Date());
                pesticide.setStatus(MasterDataStatus.APPROVED.getStatusSeq());
                pesticide.setPesticideSeq(null);
                this.pesticideRepository.save(pesticide);
                responseEntity = new ResponseEntity<>(pesticide, HttpStatus.CREATED);
            }


        }
        return responseEntity;
    }

    @Override
    public ResponseEntity updatePesticide(Pesticide pesticide) {
        Optional<Pesticide> dbPesticide = this.pesticideRepository.findById(pesticide.getPesticideSeq());
        ResponseEntity responseEntity;
        if (dbPesticide.isPresent()) {
            if (dbPesticide.get().equals(pesticide)) {
                responseEntity = new ResponseEntity<>(pesticide, HttpStatus.NOT_MODIFIED);
            } else {
                Set<ConstraintViolation<Pesticide>> errors = Validation.buildDefaultValidatorFactory().
                        getValidator().validate(pesticide);
                if (errors.size() > 0) {
//                    String errorMessage = ErrorMessageCreator.errorsInRow((HashSet<?>) errors);
                    responseEntity = new ResponseEntity<>("Pesticide already exist", HttpStatus.BAD_REQUEST);
                } else {
                    pesticide = this.pesticideRepository.save(pesticide);
                    responseEntity = new ResponseEntity<>(pesticide, HttpStatus.CREATED);
                }
            }
        } else {
            responseEntity = new ResponseEntity<>(pesticide, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Pesticide> deletePesticide(Integer pesticideSeq) {
        Optional<Pesticide> dbPesticide = this.pesticideRepository.findById(pesticideSeq);
        ResponseEntity<Pesticide> responseEntity;
        if (dbPesticide.isPresent()) {
            Pesticide pesticide = dbPesticide.get();
            pesticide.setStatus(MasterDataStatus.DELETED.getStatusSeq());
            this.pesticideRepository.save(pesticide);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public List<Pesticide> findAllPesticides(Integer statusSeq) {
        return this.pesticideRepository.findByStatus(statusSeq);
    }
}
