package com.fft.farm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User extends SharedModel {


    private Integer userSeq;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String secondName;
    private Set<Role> roles;

    public User() {
    }

    public User(User user) {
        this.userSeq=user.getUserSeq();
        this.email= user.getEmail();
        this.firstName = user.getFirstName();
        this.secondName= user.getSecondName();
        this.roles= user.getRoles();
        this.username=user.getUsername();
        this.password=user.getPassword();

    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_seq")
    public Integer getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(Integer userSeq) {
        this.userSeq = userSeq;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 250)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 250)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    @Basic
    @Column(name = "second_name")
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_seq"))
    public Set<Role> getRoles() {
        return roles;
    }


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
