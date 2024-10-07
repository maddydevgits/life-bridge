package com.makeskilled.LifeBridge;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.TemporalType;
import jakarta.persistence.*;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "blood_inventory")
public class BloodInventoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="hospital_name")
    private String hospitalName;

    @Column(name="contact_number")
    private String hospitalNo;

    @Column(name = "blood_type")
    private String bloodType;

    private int quantity;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // Add this annotation
    private Date expirationDate;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHospitalNo(){
        return hospitalNo;
    }

    public void setHospitalNo(String hospitalNo){
        this.hospitalNo=hospitalNo;
    }

    public String getHospitalName(){
        return hospitalName;
    }

    public void setHospitalName(String hospitalName){
        this.hospitalName=hospitalName;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}