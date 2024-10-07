package com.makeskilled.LifeBridge;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "request_history")
public class BloodRequestHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "blood_request_id")
    private BloodRequestModel bloodRequest;

    private String acceptedBy;
    private String acceptedByMobile;

    @Temporal(TemporalType.TIMESTAMP)
    private Date acceptedAt;

    // Constructors, Getters, Setters
    public BloodRequestHistory() {}

    public BloodRequestHistory(BloodRequestModel bloodRequest, String acceptedBy, String acceptedByMobile, Date acceptedAt) {
        this.bloodRequest = bloodRequest;
        this.acceptedBy = acceptedBy;
        this.acceptedByMobile = acceptedByMobile;
        this.acceptedAt = acceptedAt;
    }

    // Getters and Setters
    public BloodRequestModel getBloodRequest() {
        return bloodRequest;
    }

    public void setBloodRequest(BloodRequestModel bloodRequest) {
        this.bloodRequest = bloodRequest;
    }

    public String getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(String acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public String getAcceptedByMobile() {
        return acceptedByMobile;
    }

    public void setAcceptedByMobile(String acceptedByMobile) {
        this.acceptedByMobile = acceptedByMobile;
    }

    public Date getAcceptedAt() {
        return acceptedAt;
    }

    public void setAcceptedAt(Date acceptedAt) {
        this.acceptedAt = acceptedAt;
    }
}
