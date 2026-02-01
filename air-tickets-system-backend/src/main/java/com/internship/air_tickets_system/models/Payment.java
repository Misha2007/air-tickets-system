package com.internship.air_tickets_system.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private double summa;

    public enum StaatusEnum {
        SUCCESS, FAILED;
    }

    public enum MeetodEnum {
        PAYPAL, GOOGLEPAY, APPLEPAY;
    }
    
    @Enumerated(EnumType.STRING)
    @Column()
    private StaatusEnum staatus;

    @Column()
    private MeetodEnum meetod;


    public Payment(Long id, double summa, StaatusEnum staatus, MeetodEnum meetod) {
        this.id = id;
        this.summa = summa;
        this.staatus = staatus;
        this.meetod = meetod;
    }

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSumma() {
        return summa;
    }

    public void setSumma(double summa) {
        this.summa = summa;
    }

    public StaatusEnum getStaatus() {
        return staatus;
    }

    public void setStaatus(StaatusEnum staatus) {
        this.staatus = staatus;
    }

    public MeetodEnum getMeetod() {
        return meetod;
    }

    public void setMeetod(MeetodEnum meetod) {
        this.meetod = meetod;
    }
}
