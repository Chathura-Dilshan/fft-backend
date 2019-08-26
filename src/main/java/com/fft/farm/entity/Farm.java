package com.fft.farm.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
@Entity
@Table(name = "farm")
@EntityListeners(AuditingEntityListener.class)
public class Farm {

    private Integer farmSeq;
    private String farmName;
    private String address;
    private String contactNo;
    private Integer fertilizerSeq;
    private Integer pesticideSeq;
    private Fertilizer fertilizer;
    private  Pesticide pesticide;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "farm_seq", nullable = false, unique = true)
    public Integer getFarmSeq() {
        return farmSeq;
    }

    public void setFarmSeq(Integer farmSeq) {
        this.farmSeq = farmSeq;
    }
    @Basic
    @Column(name = "farm_name")
    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }
    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Basic
    @Column(name = "contact_no")
    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    @Basic
    @Column(name = "fertilizer_seq")
    public Integer getFertilizerSeq() {
        return fertilizerSeq;
    }

    public void setFertilizerSeq(Integer fertilizerSeq) {
        this.fertilizerSeq = fertilizerSeq;
    }
    @Basic
    @Column(name = "pesticide_seq")
    public Integer getPesticideSeq() {
        return pesticideSeq;
    }

    public void setPesticideSeq(Integer pesticideSeq) {
        this.pesticideSeq = pesticideSeq;
    }
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fertilizer_seq", nullable = false, insertable = false, updatable = false)
    public Fertilizer getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(Fertilizer fertilizer) {
        this.fertilizer = fertilizer;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pesticide_seq",nullable = false, insertable = false, updatable = false)
    public Pesticide getPesticide() {
        return pesticide;
    }

    public void setPesticide(Pesticide pesticide) {
        this.pesticide = pesticide;
    }
}
