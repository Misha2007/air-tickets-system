package com.internship.air_tickets_system.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
// import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String flightnumber;

    @Column(nullable = false)
    private String saabumiskoht;

    @Column(nullable = false)
    private String sihtkoht;

    @Column(nullable = false)
    private String saabumiskohtcode;

    @Column(nullable = false)
    private String sihtkohtcode;

    @Column(nullable = false)
    private LocalDateTime lahkumiseaeg;

    @Column(nullable = false)
    private LocalDateTime saabumiseaeg;

    @Column(nullable = true)
    private double hind;

    @OneToMany(mappedBy = "flight") 
    @JsonManagedReference
    private List<Seat> istmed;

    @ManyToOne 
    @JoinColumn(name = "saabumislennuJId", nullable = false)
    private Airport saabumislennuJId;

    @ManyToOne 
    @JoinColumn(name = "sihtlennuJId", nullable = false)
    private Airport sihtlennuJId;
    
    @ManyToOne 
    @JoinColumn(name = "airplane_id", nullable = false)
    private Airplane airplane;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(String flightnumber) {
        this.flightnumber = flightnumber;
    }

    public String getSaabumiskoht() {
        return saabumiskoht;
    }

    public void setSaabumiskoht(String saabumiskoht) {
        this.saabumiskoht = saabumiskoht;
    }

    public String getSihtkoht() {
        return sihtkoht;
    }

    public void setSihtkoht(String sihtkoht) {
        this.sihtkoht = sihtkoht;
    }

    public String getSaabumiskohtcode() {
        return saabumiskohtcode;
    }

    public void setSaabumiskohtcode(String saabumiskohtcode) {
        this.saabumiskohtcode = saabumiskohtcode;
    }

    public String getSihtkohtcode() {
        return sihtkohtcode;
    }

    public void setSihtkohtcode(String sihtkohtcode) {
        this.sihtkohtcode = sihtkohtcode;
    }

    public LocalDateTime getLahkumiseaeg() {
        return lahkumiseaeg;
    }

    public void setLahkumiseaeg(LocalDateTime lahkumiseaeg) {
        this.lahkumiseaeg = lahkumiseaeg;
    }

    public LocalDateTime getSaabumiseaeg() {
        return saabumiseaeg;
    }

    public void setSaabumiseaeg(LocalDateTime saabumiseaeg) {
        this.saabumiseaeg = saabumiseaeg;
    }

    public double getHind() {
        return hind;
    }

    public void setHind(double hind) {
        this.hind = hind;
    }

    public List<Seat> getIstmed() {
        return istmed;
    }

    public void setIstmed(List<Seat> istmed) {
        this.istmed = istmed;
    }
}
