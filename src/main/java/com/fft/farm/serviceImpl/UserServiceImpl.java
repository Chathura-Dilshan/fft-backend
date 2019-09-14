package com.fft.farm.serviceImpl;

import com.fft.farm.entity.Role;
import com.fft.farm.entity.User;
import com.fft.farm.repository.RoleRepository;
import com.fft.farm.repository.UserRepository;
import com.fft.farm.service.UserService;
import com.fft.farm.util.MasterDataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public ResponseEntity createUser(User user) {
        ResponseEntity responseEntity;
        Optional<User> existUserName = this.userRepository.findByUsername(user.getUsername());
        Set<ConstraintViolation<User>> errors = Validation.buildDefaultValidatorFactory().
                getValidator().validate(user);
        if (errors.size() > 0) {
//            String errorMessage = ErrorMessageCreator.errorsInRow((HashSet<?>) errors);
            responseEntity = new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);
        } else {

            if (existUserName.isPresent()) {
                responseEntity = new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);

            } else {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                user.setCreatedBy(username);
                user.setCreatedDate(new Date());
                user.setLastModifiedBy(username);
                user.setLastModifiedDate(new Date());
                user.setStatus(MasterDataStatus.APPROVED.getStatusSeq());
                user.setUserSeq(null);
                Set<Role> rolesSet = new HashSet<>();
                Optional<List<Role>> existRoles;
                if (user.getUserType().equals("GUEST_USER")) {
                    user.setUserType("GUEST_USER");
                    List<String> roles = new ArrayList<>();
                    roles.add("login");
                    roles.add("dashboard");
                    roles.add("bodyMassIndex");
                    roles.add("createBodyMassIndex");
                    existRoles = this.roleRepository.findByRoleNameIn(roles);
                    existRoles.ifPresent(rolesSet::addAll);

                } else {
                    user.setUserType(user.getUserType());
                    existRoles = this.roleRepository.findByRoleSeqIn(user.getRolesList());
                    existRoles.ifPresent(rolesSet::addAll);
                }
                user.setRoles(rolesSet);


                this.userRepository.save(user);
                responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
            }


        }
        return responseEntity;
    }

    @Override
    public ResponseEntity updateUser(User user) {
        return null;
    }

    @Override
    public ResponseEntity<User> deleteUser(Integer userSeq) {
        return null;
    }

    @Override
    public List<User> findAllUsers(Integer statusSeq) {
        return  this.userRepository.findByStatus(statusSeq);
    }

    @Override
    public User findByUsernameAndStatus(String username, Integer statusSeq) {
        return  this.userRepository.findByUsernameAndStatus(username,statusSeq);
    }


}
