package com.internship.air_tickets_system.models;

import java.time.LocalDate;

public class Flights {
    private String Sihtkoht;
    private LocalDate Kuupaev;
    private int Lennuaeg;
    private Double Hind;

    public Flights() {}

    public Flights(String Sihtkoht, LocalDate  Kuupaev, int Lennuaeg, Double Hind) {
        this.Sihtkoht = Sihtkoht;
        this.Kuupaev = Kuupaev;
        this.Lennuaeg = Lennuaeg;
        this.Hind = Hind;
    }

    public String getSihtkoht() {
        return Sihtkoht;
    }

    public void setSihtkoht(String sihtkoht) {
        this.Sihtkoht = sihtkoht;
    }

    public LocalDate getKuupaev() {
        return Kuupaev;
    }

    public void setKuupaev(LocalDate kuupaev) {
        this.Kuupaev = kuupaev;
    }

    public int getLennuaeg() {
        return Lennuaeg;
    }

    public void setLennuaeg(int lennuaeg) {
        this.Lennuaeg = lennuaeg;
    }

    public Double getHind() {
        return Hind;
    }

    public void setHind(Double hind) {
        this.Hind = hind;
    }
}
