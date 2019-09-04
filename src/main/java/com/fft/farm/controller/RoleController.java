package com.fft.farm.controller;

import com.fft.farm.entity.Role;
import com.fft.farm.service.RoleService;
import com.fft.farm.util.MasterDataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('createRole')")
    public ResponseEntity createRole(@RequestBody Role role) {
        return this.roleService.createRole(role);
    }

    @GetMapping("/findRoleByStatus")
    @PreAuthorize("hasAnyRole('login','createRole')")
    public List<Role> findRoleByStatus() {
        return this.roleService.findRoleByStatus(MasterDataStatus.APPROVED.getStatusSeq());
    }


}
