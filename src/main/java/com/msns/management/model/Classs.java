package com.msns.management.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Classs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classId;

    @Column(unique = true)
    private String className;

    @JsonIgnore
    @OneToMany(mappedBy = "classInfo")
    private List<StudentClass> studentClass;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
