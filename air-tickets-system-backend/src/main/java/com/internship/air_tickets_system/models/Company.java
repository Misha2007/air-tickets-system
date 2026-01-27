package com.internship.air_tickets_system.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String aadress;

    public Company(int number, String aadress) {
        this.id = id;
        this.aadress = aadress;
    }

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAadress() {
        return aadress;
    }

    public void setAadress(String aadress) {
        this.aadress = aadress;
    }
}
