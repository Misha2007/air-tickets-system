package com.internship.air_tickets_system.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "city")
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String linnaNimi;

    @OneToMany(mappedBy = "city")
    @JsonManagedReference
    private List<Airport> airports;


    public City(Long id, String linnaNimi) {
        this.id = id;
        this.linnaNimi = linnaNimi;
    }

    public City() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinnNimi() {
        return linnaNimi;
    }

    public void setLinnNimi(String linnaNimi) {
        this.linnaNimi = linnaNimi;
    }
}
