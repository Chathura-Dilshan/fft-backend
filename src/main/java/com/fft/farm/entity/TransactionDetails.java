package com.fft.farm.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Entity
@Table(name = "transaction_details")
@EntityListeners(AuditingEntityListener.class)
public class TransactionDetails {
    private Integer transactionDetailsSeq;
    //farm details
    @NotNull
    private Integer farmSeq;
    private String note;
    private Date qrGenerateDate;
    // transport details
    private String vehicleNo;
    private String vehicleType;// van,lorry
    private Date transporterPickDate;
    private Date transporterDeliveredDate;
    //packing details
    private Date packingReceivedDate;
    private Date packingDeliveredDate;
    private Integer packingLocationSeq;
    //warehouse details
    private Date warehouseReceivedDate;
    private Date warehouseDeliveredDate;
    private Integer warehouseLocationSeq;
    //supermarket details
    private Date supermarketReceivedDate;
    private Integer supermarketLocationSeq;

    private Integer currentStatus;

    private Farm farm;




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_details_seq", nullable = false, unique = true)
    public Integer getTransactionDetailsSeq() {
        return transactionDetailsSeq;
    }

    public void setTransactionDetailsSeq(Integer transactionDetailsSeq) {
        this.transactionDetailsSeq = transactionDetailsSeq;
    }

    @Basic
    @Column(name = "farm_seq")
    public Integer getFarmSeq() {
        return farmSeq;
    }

    public void setFarmSeq(Integer farmSeq) {
        this.farmSeq = farmSeq;
    }


    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    @Basic
    @Column(name = "qr_generate_date")
    public Date getQrGenerateDate() {
        return qrGenerateDate;
    }

    public void setQrGenerateDate(Date qrGenarateDate) {
        this.qrGenerateDate = qrGenarateDate;
    }
    @Basic
    @Column(name = "vehicleNo")
    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }
    @Basic
    @Column(name = "vehicleType")
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Basic
    @Column(name = "transporter_pick_date")
    public Date getTransporterPickDate() {
        return transporterPickDate;
    }

    public void setTransporterPickDate(Date transporterPickDate) {
        this.transporterPickDate = transporterPickDate;
    }
    @Basic
    @Column(name = "transporter_delivered_date")
    public Date getTransporterDeliveredDate() {
        return transporterDeliveredDate;
    }

    public void setTransporterDeliveredDate(Date transporterDeliveredDate) {
        this.transporterDeliveredDate = transporterDeliveredDate;
    }

    @Basic
    @Column(name = "packing_received_date")
    public Date getPackingReceivedDate() {
        return packingReceivedDate;
    }

    public void setPackingReceivedDate(Date packingReceivedDate) {
        this.packingReceivedDate = packingReceivedDate;
    }
    @Basic
    @Column(name = "packing_delivered_date")
    public Date getPackingDeliveredDate() {
        return packingDeliveredDate;
    }

    public void setPackingDeliveredDate(Date packingDeliveredDate) {
        this.packingDeliveredDate = packingDeliveredDate;
    }
    @Basic
    @Column(name = "packing_location_seq")
    public Integer getPackingLocationSeq() {
        return packingLocationSeq;
    }

    public void setPackingLocationSeq(Integer packingLocationSeq) {
        this.packingLocationSeq = packingLocationSeq;
    }
    @Basic
    @Column(name = "warehouse_received_date")
    public Date getWarehouseReceivedDate() {
        return warehouseReceivedDate;
    }

    public void setWarehouseReceivedDate(Date warehouseReceivedDate) {
        this.warehouseReceivedDate = warehouseReceivedDate;
    }
    @Basic
    @Column(name = "warehouse_delivered_date")
    public Date getWarehouseDeliveredDate() {
        return warehouseDeliveredDate;
    }

    public void setWarehouseDeliveredDate(Date warehouseDeliveredDate) {
        this.warehouseDeliveredDate = warehouseDeliveredDate;
    }
    @Basic
    @Column(name = "warehouse_location_seq")
    public Integer getWarehouseLocationSeq() {
        return warehouseLocationSeq;
    }

    public void setWarehouseLocationSeq(Integer warehouseLocationSeq) {
        this.warehouseLocationSeq = warehouseLocationSeq;
    }
    @Basic
    @Column(name = "supermarket_received_date")
    public Date getSupermarketReceivedDate() {
        return supermarketReceivedDate;
    }

    public void setSupermarketReceivedDate(Date supermarketReceivedDate) {
        this.supermarketReceivedDate = supermarketReceivedDate;
    }
    @Basic
    @Column(name = "supermarket_location_seq")
    public Integer getSupermarketLocationSeq() {
        return supermarketLocationSeq;
    }

    public void setSupermarketLocationSeq(Integer supermarketLocationSeq) {
        this.supermarketLocationSeq = supermarketLocationSeq;
    }
    @Basic
    @Column(name = "currentStatus")
    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }
    @OneToOne
    @JoinColumn(name = "farm_seq",nullable = false, insertable = false, updatable = false)
    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }
}
