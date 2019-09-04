package com.fft.farm.serviceImpl;

import com.fft.farm.entity.Role;
import com.fft.farm.repository.RoleRepository;
import com.fft.farm.service.RoleService;
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
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public ResponseEntity createRole(Role role) {
        ResponseEntity responseEntity;
        Optional<Role> existRoleName = this.roleRepository.findByRoleName(role.getRoleName());
        Set<ConstraintViolation<Role>> errors = Validation.buildDefaultValidatorFactory().
                getValidator().validate(role);
        if (errors.size() > 0) {
//            String errorMessage = ErrorMessageCreator.errorsInRow((HashSet<?>) errors);
            responseEntity = new ResponseEntity<>("Role already exist", HttpStatus.BAD_REQUEST);
        } else {

            if (existRoleName.isPresent()) {
                responseEntity = new ResponseEntity<>("Role already exist", HttpStatus.BAD_REQUEST);

            } else {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                role.setCreatedBy(username);
                role.setCreatedDate(new Date());
                role.setLastModifiedBy(username);
                role.setLastModifiedDate(new Date());
                role.setStatus(MasterDataStatus.APPROVED.getStatusSeq());
                role.setRoleSeq(null);
                this.roleRepository.save(role);
                responseEntity = new ResponseEntity<>(role, HttpStatus.CREATED);
            }


        }
        return responseEntity;
    }

    @Override
    public List<Role> findRoleByStatus(Integer statusSeq) {
        return this.roleRepository.findByStatus(statusSeq);
    }
}
