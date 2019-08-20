package com.fft.farm.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
@Entity
@Table(name = "body_mass_index")
@EntityListeners(AuditingEntityListener.class)
public class BodyMassIndex extends SharedModel{
    private Integer bodyMassIndexSeq;
    private Double height;
    private Double weight;
    private Integer userSeq;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "body_mass_index_seq", nullable = false, unique = true)
    public Integer getBodyMassIndexSeq() {
        return bodyMassIndexSeq;
    }

    public void setBodyMassIndexSeq(Integer bodyMassIndexSeq) {
        this.bodyMassIndexSeq = bodyMassIndexSeq;
    }
    @Basic
    @Column(name = "height", nullable = false, length = 50)
    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
    @Basic
    @Column(name = "weight", nullable = false, length = 50)
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
    @Basic
    @Column(name = "user_seq", nullable = false, length = 50)
    public Integer getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(Integer userSeq) {
        this.userSeq = userSeq;
    }
    @OneToOne
    @JoinColumn(name = "user_seq", insertable = false, updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
