package com.makeskilled.LifeBridge;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blood_requests")
public class BloodRequestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private LifeBridgeModel requester;

    private String username;  // User who made the request
    private String bloodType;
    private int requestedQuantity;
    private boolean accepted;  // Whether the request has been accepted or not

    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;

    private String acceptedBy;  // The user or hospital that accepts the request
    private String acceptedByMobile;  // Mobile number of the user who accepted

    // Constructors, Getters, Setters
    public LifeBridgeModel getRequester() {
        return requester;
    }

    public void setRequester(LifeBridgeModel requester2) {
        this.requester = requester2;
    }
   

    // Constructors, Getters, Setters
    public BloodRequestModel() {}

    public BloodRequestModel(String username, String bloodType, int requestedQuantity, Date requestDate) {
        this.username = username;
        this.bloodType = bloodType;
        this.requestedQuantity = requestedQuantity;
        this.requestDate = requestDate;
        this.accepted = false;
    }

     // Getters and Setters
    public String getAcceptedByMobile() {
        return acceptedByMobile;
    }

    public void setAcceptedByMobile(String acceptedByMobile) {
        this.acceptedByMobile = acceptedByMobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(int requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(String acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
}
