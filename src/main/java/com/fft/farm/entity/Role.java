package com.fft.farm.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "role")
public class Role extends SharedModel{
    private Integer roleSeq;
    private String role_name;

    public Role() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_seq")
    public Integer getRoleSeq() {
        return roleSeq;
    }

    public void setRoleSeq(Integer roleSeq) {
        this.roleSeq = roleSeq;
    }

    @Basic
    @Column(name = "role_name", nullable = false, length = 250)
    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleSeq, role.roleSeq) &&
                Objects.equals(role_name, role.role_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleSeq, role_name);
    }
}
