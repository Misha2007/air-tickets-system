package com.internship.air_tickets_system.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "airport")
public class Airport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String lennuJKood;

    @ManyToOne 
    @JoinColumn(name = "city_id", nullable = false)
    @JsonBackReference
    private City city;

    public Airport(Long id, String lennuJKood, City city) {
        this.id = id;
        this.lennuJKood = lennuJKood;
        this.city = city;
    }

    public Airport() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getlennuJKood() {
        return lennuJKood;
    }

    public void setLennuJKood(String lennuJKood) {
        this.lennuJKood = lennuJKood;
    }

    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }
    
}

