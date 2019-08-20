package com.fft.farm.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
@Entity
@Table(name = "pesticide")
@EntityListeners(AuditingEntityListener.class)
public class Pesticide extends SharedModel {
    private Integer pesticideSeq;
    private String pesticideName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pesticide_seq",nullable = false, unique = true)
    public Integer getPesticideSeq() {
        return pesticideSeq;
    }

    public void setPesticideSeq(Integer pesticideSeq) {
        this.pesticideSeq = pesticideSeq;
    }
    @Basic
    @Column(name = "pesticide_name")
    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }
}
