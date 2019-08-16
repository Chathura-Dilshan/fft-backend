package com.fft.farm.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
//@Entity
//@Table(name = "user_role")
//@EntityListeners(AuditingEntityListener.class)
public class UserRole extends SharedModel{
    private Integer userRoleSeq;
    private Integer userSeq;
    private Integer roleSeq;
    private List<Role> roleList;
    private User user;
    private Role role;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_seq", nullable = false, precision = 0, unique = true)
    public Integer getUserRoleSeq() {
        return userRoleSeq;
    }

    public void setUserRoleSeq(Integer userRoleSeq) {
        this.userRoleSeq = userRoleSeq;
    }
    @Basic
    @Column(name = "user_seq", nullable = false, length = 50)
    public Integer getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(Integer userSeq) {
        this.userSeq = userSeq;
    }

    @Basic
    @Column(name = "role_seq", nullable = false, length = 50)
    public Integer getRoleSeq() {
        return roleSeq;
    }

    public void setRoleSeq(Integer roleSeq) {
        this.roleSeq = roleSeq;
    }
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_role_seq", nullable = false)
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }



    @OneToOne
    @JoinColumn(name = "user_seq", insertable = false, updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @OneToOne
    @JoinColumn(name = "role_seq", insertable = false, updatable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
