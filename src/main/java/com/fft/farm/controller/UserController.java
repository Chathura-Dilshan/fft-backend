package com.fft.farm.controller;

import com.fft.farm.entity.CustomUserDetails;
import com.fft.farm.entity.User;
import com.fft.farm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/check")
    public void userLogin(@RequestBody CustomUserDetails user) {
        System.out.println("aaaaaaaa");

    }

    @GetMapping("/secured/all1")
    public String user() {
        return "Hell i am Chathura ssssssssss";

    }

    @GetMapping(produces = "application/json")
    @RequestMapping({"/login"})
    @PreAuthorize("hasAnyRole('login')")
    public User validateLogin(@RequestBody User user) {
        User user1;
        Optional<User> optionalUser = this.userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (optionalUser.isPresent()) {
            user1 = optionalUser.get();
        } else {
            user1 = null;
        }

        return user1;
    }


}
