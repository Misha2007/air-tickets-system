package com.internship.air_tickets_system.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "seat")
public class Seat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private boolean lValjapaas;

    @Column(nullable = false)
    private boolean jalaRuum;

    @Column(nullable = false)
    private int klass;

    @Column(nullable = false)
    private boolean vaba;

    @ManyToOne 
    @JoinColumn(name = "flight_id", nullable = false)
    @JsonBackReference
    private Flight flight;

    public Seat(int number, boolean lValjapaas, boolean jalaRuum, int klass, Flight flight, boolean vaba) {
        this.number = number;
        this.lValjapaas = lValjapaas;
        this.jalaRuum = jalaRuum;
        this.klass = klass;
        this.flight = flight;
        this.vaba = vaba;
    }

    public Seat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean islValjapaas() {
        return lValjapaas;
    }

    public void setlValjapaas(boolean lValjapaas) {
        this.lValjapaas = lValjapaas;
    }

    public boolean isJalaRuum() {
        return jalaRuum;
    }

    public void setJalaRuum(boolean jalaRuum) {
        this.jalaRuum = jalaRuum;
    }

    public int getKlass() {
        return klass;
    }

    public void setKlass(int klass) {
        this.klass = klass;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public boolean getVaba() {
        return vaba;
    }

    public void setVaba(boolean vaba) {
        this.vaba = vaba;
    }
}
