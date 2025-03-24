package com.internship.air_tickets_system.models;

public class Seats {
    private int Number;
    private boolean LValjapaas;
    private boolean JalaRuum;
    private int Klass;
    private int LennuId;

    public Seats() {}

    public Seats(int Number, boolean LValjapaas, boolean JalaRuum, int Klass, int LennuId) {
        this.Number = Number;
        this.LValjapaas = LValjapaas;
        this.JalaRuum = JalaRuum;
        this.Klass = Klass;
        this.LennuId = LennuId;
    }

    public boolean getLValjapaas() {
        return LValjapaas;
    }

    public void setLValjapaas(boolean LValjapaas) {
        this.LValjapaas = LValjapaas;
    }

    public boolean getJalaRuum() {
        return JalaRuum;
    }

    public void setJalaRuum(boolean JalaRuum) {
        this.JalaRuum = JalaRuum;
    }

    public int getKlass() {
        return Klass;
    }

    public void setKlass(int Klass) {
        this.Klass = Klass;
    }
    public int getNumber() {
        return Number;
    }

    public void setNumber(int Number) {
        this.Number = Number;
    }

    public int getLennuId() {
        return LennuId;
    }

    public void setLennuId(int LennuId) {
        this.LennuId = LennuId;
    }
}
