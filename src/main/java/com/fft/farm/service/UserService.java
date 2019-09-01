package com.fft.farm.service;

import com.fft.farm.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity createUser(User user);

    ResponseEntity updateUser(User user);

    ResponseEntity<User> deleteUser(Integer userSeq);

    List<User> findAllUsers(Integer statusSeq);
}
