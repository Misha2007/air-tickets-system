package com.internship.air_tickets_system.models;

import java.time.LocalDate;

public class Flights {
    private int id;
    private String Saabumiskoht;
    private String Sihtkoht;
    private LocalDate Kuupaev;
    private int Lennuaeg;
    private Double Hind;
    private int Istmed;

    public Flights() {}

    public Flights(int id, String Saabumiskoht, String Sihtkoht, LocalDate  Kuupaev, int Lennuaeg, Double Hind, int Istmed) {
        this.id = id;
        this.Saabumiskoht = Saabumiskoht;
        this.Sihtkoht = Sihtkoht;
        this.Kuupaev = Kuupaev;
        this.Lennuaeg = Lennuaeg;
        this.Hind = Hind;
        this.Istmed = Istmed;
    }

    public String getSaabumiskoht() {
        return Saabumiskoht;
    }

    public void setSaabumiskoht(String Saabumiskoht) {
        this.Saabumiskoht = Saabumiskoht;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIstmed() {
        return Istmed;
    }

    public void setIstmed(int Istmed) {
        this.Istmed = Istmed;
    }
}
