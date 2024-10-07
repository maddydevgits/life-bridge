package com.makeskilled.LifeBridge;

import jakarta.persistence.*;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "donations")
public class DonationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String donorName;

    private String donationType;  // e.g., "Blood" or "Fund"

    private double amount;  // Use if it's a financial donation

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // Add this annotation
    private Date donationDate;

    // Constructors, Getters, Setters
    public DonationModel() {}

    public DonationModel(String donorName, String donationType, double amount, Date donationDate) {
        this.donorName = donorName;
        this.donationType = donationType;
        this.amount = amount;
        this.donationDate = donationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }
}
