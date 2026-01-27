package com.internship.air_tickets_system.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "airplane")
public class Airplane implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String regNum;

    @Column(nullable = false)
    private String model;

    @ManyToOne 
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public Airplane(Long id, String regNum, String model) {
        this.id = id;
        this.regNum = regNum;
        this.model = model;
    }

    public Airplane() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
