package com.fft.farm.serviceImpl;

import com.fft.farm.entity.Location;
import com.fft.farm.entity.User;
import com.fft.farm.repository.LocationRepository;
import com.fft.farm.repository.UserRepository;
import com.fft.farm.service.LocationService;
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
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository,
                               UserRepository userRepository) {
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ResponseEntity createLocation(Location location) {
        ResponseEntity responseEntity;
        Optional<Location> existLocationName = this.locationRepository.findByLocationName(location.getLocationName());
        Set<ConstraintViolation<Location>> errors = Validation.buildDefaultValidatorFactory().
                getValidator().validate(location);
        if (errors.size() > 0) {
//            String errorMessage = ErrorMessageCreator.errorsInRow((HashSet<?>) errors);
            responseEntity = new ResponseEntity<>("Location already exist", HttpStatus.BAD_REQUEST);
        } else {

            if (existLocationName.isPresent()) {
                responseEntity = new ResponseEntity<>("Location already exist", HttpStatus.BAD_REQUEST);

            } else {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                location.setCreatedBy(username);
                location.setCreatedDate(new Date());
                location.setLastModifiedBy(username);
                location.setLastModifiedDate(new Date());
                location.setStatus(MasterDataStatus.APPROVED.getStatusSeq());
                location.setLocationSeq(null);
                this.locationRepository.save(location);
                responseEntity = new ResponseEntity<>(location, HttpStatus.CREATED);
            }


        }
        return responseEntity;
    }

    @Override
    public List<Location> findAllLocationsByUserSeq(Integer statusSeq) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> existUse = this.userRepository.findByUsername(username);
        return this.locationRepository.findByStatusAndUserSeq(statusSeq,existUse.get().getUserSeq());
    }
}
