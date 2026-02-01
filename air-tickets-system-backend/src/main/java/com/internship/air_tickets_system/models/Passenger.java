package com.internship.air_tickets_system.models;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "passenger")
public class Passenger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reisijaENimi;
    
    @Column(nullable = false)
    private String reisijaPNimi;

    @Column(nullable = false)
    private LocalDate synniP;

    @Column(nullable = false, unique = true)
    private String telefon;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String sugu;

    @Column(nullable = false, unique = true, length = 255)
    private String national_id_hash;

    @Column(nullable = false, length = 2)
    private String country_id;

    public Passenger(Long id, String reisijaENimi, String reisijaPNimi, LocalDate synniP, String telefon, String email, String sugu, String national_id_hash, String country_id) {
        this.id = id;
        this.reisijaENimi = reisijaENimi;
        this.reisijaPNimi = reisijaPNimi;
        this.synniP = synniP;
        this.telefon = telefon;
        this.email = email;
        this.sugu = sugu;
        this.national_id_hash = national_id_hash;
        this.country_id = country_id;
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

    public LocalDate getSynniP() {
        return synniP;
    }

    public void setSynniP(LocalDate synniP) {
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

    public String getCountryId() {
        return country_id;
    }

    public void setCountryId(String country_id){
        this.country_id = country_id;
    }


    public String getNationalIdHash() {
        return national_id_hash;
    }

    public void setNationalIdHash(String national_id_hash){
        this.national_id_hash = national_id_hash;
    }
}
