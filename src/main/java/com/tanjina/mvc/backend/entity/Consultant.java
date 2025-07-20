package com.tanjina.mvc.backend.entity;

import jakarta.persistence.*;

/**
 * Entity class for Consultants table.
 */
@Entity
@Table(name = "consultants")
public class Consultant
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultant_id")  // Maps to consultant_id column
    private Integer consultantId;

    @Column(name = "consultant_name")
    private String consultantName;

    @Column(name = "consultant_phone")
    private Long consultantPhone;  // Use Long for bigint

    @Column(name = "consultant_email")
    private String consultantEmail;

    // Note: consultant_role is skipped as requested

    // Constructors
    public Consultant() {}

    public Consultant(Integer consultantId, String consultantName, Long consultantPhone, String consultantEmail) {
        this.consultantId = consultantId;
        this.consultantName = consultantName;
        this.consultantPhone = consultantPhone;
        this.consultantEmail = consultantEmail;
    }

    // Getters and setters

    public Integer getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(Integer consultantId) {
        this.consultantId = consultantId;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public Long getConsultantPhone() {
        return consultantPhone;
    }

    public void setConsultantPhone(Long consultantPhone) {
        this.consultantPhone = consultantPhone;
    }

    public String getConsultantEmail() {
        return consultantEmail;
    }

    public void setConsultantEmail(String consultantEmail) {
        this.consultantEmail = consultantEmail;
    }
}