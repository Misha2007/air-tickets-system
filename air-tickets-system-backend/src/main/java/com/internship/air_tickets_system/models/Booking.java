package com.internship.air_tickets_system.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "booking")
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public enum StaatusEnum {
        IN_PROGRESS, SUCCESS, FAILED;
    }

    @Column
    @Enumerated(EnumType.STRING)
    private StaatusEnum staatus;

    public Booking(Long id, StaatusEnum staatus) {
        this.id = id;
        this.staatus = staatus;
    }

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StaatusEnum getStaatus() {
        return staatus;
    }

    public void setStaatus(StaatusEnum staatus) {
        this.staatus = staatus;
    }
}
