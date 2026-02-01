package com.internship.air_tickets_system.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "baggage")
public class Baggage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String PNimi;

    @Column()
    private int PKaal;

    @Column()
    private int PLaius;

    @Column()
    private int PPikkus;

    @Column()
    private int PKõrgus;

    @Column()
    private double PHind;



    public Baggage(Long id, String PNimi, int PKaal, int PLaius, int PPikkus, int PKõrgus, double PHind) {
        this.id = id;
        this.PNimi = PNimi;
        this.PKaal = PKaal;
        this.PLaius = PLaius;
        this.PPikkus = PPikkus;
        this.PKõrgus = PKõrgus;
        this.PHind = PHind;
    }

    public Baggage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPNimi() {
        return PNimi;
    }

    public void setPNimi(String PNimi) {
        this.PNimi = PNimi;
    }

    public int getPKaal() {
        return PKaal;
    }

    public void setPKaal(int PKaal) {
        this.PKaal = PKaal;
    }

    public int getPLaius() {
        return PLaius;
    }

    public void setPLaius(int PLaius) {
        this.PLaius = PLaius;
    }

    public int getPPikkus() {
        return PPikkus;
    }

    public void setPPikkus(int PPikkus) {
        this.PPikkus = PPikkus;
    }

    public int getPKõrgus() {
        return PKõrgus;
    }

    public void setPKõrgus(int PKõrgus) {
        this.PKõrgus = PKõrgus;
    }

    public double getPHind() {
        return PHind;
    }

    public void setPHind(double PHind) {
        this.PHind = PHind;
    }
}
