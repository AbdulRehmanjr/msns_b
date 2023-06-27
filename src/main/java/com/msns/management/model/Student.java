package com.msns.management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    private String rollNumber;
    
    private String studentName;

    private String fatherName;

    private String bForm;

    private String dateOfBirth;

    private String contactPhone;

    private String address;

    @Lob
    @Column(columnDefinition = "LONGBLOB",nullable = true)
    private byte[] picture;

    @ManyToOne
    private Classs className;

    @ManyToOne
    private Section sectionName;
    

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getbForm() {
        return bForm;
    }

    public void setbForm(String bForm) {
        this.bForm = bForm;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public Classs getClassName() {
        return className;
    }

    public void setClassName(Classs className) {
        this.className = className;
    }

    public Section getSectionName() {
        return sectionName;
    }

    public void setSectionName(Section sectionName) {
        this.sectionName = sectionName;
    }


}
