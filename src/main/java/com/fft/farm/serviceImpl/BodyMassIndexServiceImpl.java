package com.fft.farm.serviceImpl;

import com.fft.farm.entity.BodyMassIndex;
import com.fft.farm.entity.User;
import com.fft.farm.repository.BodyMassIndexRepository;
import com.fft.farm.repository.UserRepository;
import com.fft.farm.service.BodyMassIndexService;
import com.fft.farm.util.MasterDataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static jdk.nashorn.internal.objects.NativeMath.round;

@Service
public class BodyMassIndexServiceImpl implements BodyMassIndexService {
    private final BodyMassIndexRepository bodyMassIndexRepository;
    private final UserRepository userRepository;


    @Autowired
    public BodyMassIndexServiceImpl(BodyMassIndexRepository bodyMassIndexRepository,
                                    UserRepository userRepository) {
        this.bodyMassIndexRepository = bodyMassIndexRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ResponseEntity createBodyMassIndex(BodyMassIndex bodyMassIndex) {
        ResponseEntity responseEntity;

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> existUse = this.userRepository.findByUsername(username);

        Optional<BodyMassIndex> existBodyMassIndexName = this.bodyMassIndexRepository.findByUserSeqAndStatus(existUse.get().getUserSeq(), MasterDataStatus.APPROVED.getStatusSeq());
        Set<ConstraintViolation<BodyMassIndex>> errors = Validation.buildDefaultValidatorFactory().
                getValidator().validate(bodyMassIndex);
        if (errors.size() > 0) {
//            String errorMessage = ErrorMessageCreator.errorsInRow((HashSet<?>) errors);
            responseEntity = new ResponseEntity<>("BodyMassIndex already exist", HttpStatus.BAD_REQUEST);
        } else {

            if (existBodyMassIndexName.isPresent()){
                existBodyMassIndexName.get().setStatus(MasterDataStatus.DELETED.getStatusSeq());
                this.bodyMassIndexRepository.save(existBodyMassIndexName.get());
                Double weight = bodyMassIndex.getWeight();
                Double height = bodyMassIndex.getWeight();
                Integer age = bodyMassIndex.getAge();
                Double bodyStatus = bodyMassIndex.getBodyStatus();
                Double dailyCalories;

                DecimalFormat decimalFormat =new DecimalFormat("##.00");

                //Daily calories calculate

                if (bodyMassIndex.getGender() == "MALE") {

                    dailyCalories = ((weight * 2.20462) * 4.35) + ((height * 0.393701) * 4.7) - (age * 4.7) + 655;
                    dailyCalories = dailyCalories * bodyStatus;

                } else {
                    dailyCalories = ((weight * 2.20462) * 6.23) + ((height * 0.393701) * 12.7) - (age * 6.8) + 66;
                    dailyCalories = dailyCalories * bodyStatus;
                }


// 1kg = 2.20462 lbs
// 1cm = 0.393701inch

                bodyMassIndex.setCreatedBy(username);
                bodyMassIndex.setCreatedDate(new Date());
                bodyMassIndex.setLastModifiedBy(username);
                bodyMassIndex.setLastModifiedDate(new Date());
                bodyMassIndex.setStatus(MasterDataStatus.APPROVED.getStatusSeq());
                bodyMassIndex.setBodyMassIndexSeq(null);
                bodyMassIndex.setUserSeq(existUse.get().getUserSeq());
                bodyMassIndex.setDailyCalories(Double.parseDouble(decimalFormat.format(dailyCalories)));
                bodyMassIndex = this.bodyMassIndexRepository.save(bodyMassIndex);
                responseEntity = new ResponseEntity<>(bodyMassIndex, HttpStatus.CREATED);
            } else {

                Double weight = bodyMassIndex.getWeight();
                Double height = bodyMassIndex.getWeight();
                Integer age = bodyMassIndex.getAge();
                Double bodyStatus = bodyMassIndex.getBodyStatus();
                Double dailyCalories;

                DecimalFormat decimalFormat =new DecimalFormat("##.00");

                //Daily calories calculate

                if (bodyMassIndex.getGender() == "MALE") {

                    dailyCalories = ((weight * 2.20462) * 4.35) + ((height * 0.393701) * 4.7) - (age * 4.7) + 655;
                    dailyCalories = dailyCalories * bodyStatus;

                } else {
                    dailyCalories = ((weight * 2.20462) * 6.23) + ((height * 0.393701) * 12.7) - (age * 6.8) + 66;
                    dailyCalories = dailyCalories * bodyStatus;
                }


// 1kg = 2.20462 lbs
// 1cm = 0.393701inch

                bodyMassIndex.setCreatedBy(username);
                bodyMassIndex.setCreatedDate(new Date());
                bodyMassIndex.setLastModifiedBy(username);
                bodyMassIndex.setLastModifiedDate(new Date());
                bodyMassIndex.setStatus(MasterDataStatus.APPROVED.getStatusSeq());
                bodyMassIndex.setBodyMassIndexSeq(null);
                bodyMassIndex.setUserSeq(existUse.get().getUserSeq());
                bodyMassIndex.setDailyCalories(Double.parseDouble(decimalFormat.format(dailyCalories)));
                bodyMassIndex = this.bodyMassIndexRepository.save(bodyMassIndex);
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
