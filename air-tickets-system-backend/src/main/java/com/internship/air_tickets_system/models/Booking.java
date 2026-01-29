package com.internship.air_tickets_system.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public enum StaatusEnum {
        IN_PROGRESS, SUCCESS, FAILED;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StaatusEnum staatus = StaatusEnum.IN_PROGRESS;

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
