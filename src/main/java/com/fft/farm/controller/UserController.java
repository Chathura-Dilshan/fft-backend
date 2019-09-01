package com.fft.farm.controller;

import com.fft.farm.entity.User;
import com.fft.farm.repository.UserRepository;
import com.fft.farm.service.UserService;
import com.fft.farm.util.MasterDataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository,
                          UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('login','createUser')")
    public ResponseEntity createUser(@RequestBody User user) {
        return this.userService.createUser(user);

    }


    @PutMapping
    @PreAuthorize("hasAnyRole('login','user')")
    public ResponseEntity updateUser(@RequestBody User user) {
        return this.userService.updateUser(user);
    }

    @DeleteMapping("{userSeq}")
    @PreAuthorize("hasAnyRole('login','user')")
    public ResponseEntity<User> deleteUser(@PathVariable("userSeq") Integer userSeq) {
        return this.userService.deleteUser(userSeq);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('login','user')")
    public List<User> findAllUsers() {
        return this.userService.findAllUsers(MasterDataStatus.APPROVED.getStatusSeq());
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
