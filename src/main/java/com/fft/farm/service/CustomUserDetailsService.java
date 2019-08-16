package com.fft.farm.service;

import com.fft.farm.entity.CustomUserDetails;
import com.fft.farm.entity.User;
import com.fft.farm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private  UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser =userRepository.findByUsername(username);
        optionalUser
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        return optionalUser
                .map(CustomUserDetails::new).get();
    }
}
