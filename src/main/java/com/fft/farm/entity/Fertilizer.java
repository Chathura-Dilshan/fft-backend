package com.fft.farm.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
@Entity
@Table(name = "fertilizer")
@EntityListeners(AuditingEntityListener.class)

public class Fertilizer extends SharedModel{
    private Integer fertilizerSeq;
    private String fertilizerName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fertilizer_seq", nullable = false, unique = true)
    public Integer getFertilizerSeq() {
        return fertilizerSeq;
    }

    public void setFertilizerSeq(Integer fertilizerSeq) {
        this.fertilizerSeq = fertilizerSeq;
    }

    @Basic
    @Column(name = "fertilizer_name")
    public String getFertilizerName() {
        return fertilizerName;
    }

    public void setFertilizerName(String fertilizerName) {
        this.fertilizerName = fertilizerName;
    }
}
