package com.internship.air_tickets_system.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "passenger")
public class Passenger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String reisijaENimi;
    
    @Column()
    private String reisijaPNimi;

    @Column()
    private String synniP;

    @Column()
    private String telefon;

    @Column()
    private String email;

    @Column()
    private String sugu;

    public Passenger(Long id, String reisijaENimi, String reisijaPNimi, String synniP, String telefon, String email, String sugu) {
        this.id = id;
        this.reisijaENimi = reisijaENimi;
        this.reisijaPNimi = reisijaPNimi;
        this.synniP = synniP;
        this.telefon = telefon;
        this.email = email;
        this.sugu = sugu;
    }

    public Passenger() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReisijaENimi() {
        return reisijaENimi;
    }

    public void setReisijaENimi(String reisijaENimi) {
        this.reisijaENimi = reisijaENimi;
    }

    public String getReisijaPNimi() {
        return reisijaPNimi;
    }

    public void setReisijaPNimi(String reisijaPNimi) {
        this.reisijaPNimi = reisijaPNimi;
    }

    public String getSynniP() {
        return synniP;
    }

    public void setSynniP(String synniP) {
        this.synniP = synniP;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSugu() {
        return sugu;
    }

    public void setSugu(String sugu) {
        this.sugu = sugu;
    }
}
