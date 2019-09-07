package com.fft.farm.repository;

import com.fft.farm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);


    List<User> findByStatus(Integer statusSeq);

    User findByUsernameAndStatus(String username, Integer statusSeq);
}
