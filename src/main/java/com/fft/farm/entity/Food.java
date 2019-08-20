package com.fft.farm.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
@Entity
@Table(name = "food")
@EntityListeners(AuditingEntityListener.class)
public class Food extends SharedModel{
    private Integer foodSeq;
    private String foodName;
    private Double calorie;
    private Double fat;
    private Double cholesterol;
    private Double sodium;
    private Double protein;
    private Double carbohydrate;
    private Double vitaminA;
    private Double vitaminC;
    private Double iron;
    private Double calcium;
    private Double sugars;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "food_seq", nullable = false, unique = true)
    public Integer getFoodSeq() {
        return foodSeq;
    }

    public void setFoodSeq(Integer foodSeq) {
        this.foodSeq = foodSeq;
    }
    @Basic
    @Column(name = "food_name")
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    @Basic
    @Column(name = "calorie")
    public Double getCalorie() {
        return calorie;
    }

    public void setCalorie(Double calorie) {
        this.calorie = calorie;
    }
    @Basic
    @Column(name = "fat")
    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }
    @Basic
    @Column(name = "cholesterol")
    public Double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Double cholesterol) {
        this.cholesterol = cholesterol;
    }
    @Basic
    @Column(name = "sodium")
    public Double getSodium() {
        return sodium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }
    @Basic
    @Column(name = "protein")
    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }
    @Basic
    @Column(name = "carbohydrate")
    public Double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }
    @Basic
    @Column(name = "vitamin_A")
    public Double getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(Double vitaminA) {
        this.vitaminA = vitaminA;
    }
    @Basic
    @Column(name = "vitamin_C")
    public Double getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(Double vitaminC) {
        this.vitaminC = vitaminC;
    }
    @Basic
    @Column(name = "iron")
    public Double getIron() {
        return iron;
    }

    public void setIron(Double iron) {
        this.iron = iron;
    }
    @Basic
    @Column(name = "calcium")
    public Double getCalcium() {
        return calcium;
    }

    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }
    @Basic
    @Column(name = "sugars")
    public Double getSugars() {
        return sugars;
    }

    public void setSugars(Double sugars) {
        this.sugars = sugars;
    }
}
