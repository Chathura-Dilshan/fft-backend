package com.fft.farm.service;

import com.fft.farm.entity.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {
    ResponseEntity createRole(Role role);

    List<Role> findRoleByStatus(Integer statusSeq);
}
