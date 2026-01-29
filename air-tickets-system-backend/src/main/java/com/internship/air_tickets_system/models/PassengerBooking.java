package com.internship.air_tickets_system.models;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "passenger_booking")
public class PassengerBooking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reisijaId", referencedColumnName = "id")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "IsteId", referencedColumnName = "id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "PagaasId", referencedColumnName = "id")
    private Baggage baggage;

    @ManyToOne
    @JoinColumn(name = "BookingId", referencedColumnName = "id")
    private Booking booking;

    public PassengerBooking() {
    }

    public PassengerBooking(Long id, Passenger passenger, Seat seat, Baggage baggage, Booking booking) {
        this.id = id;
        this.passenger = passenger;
        this.seat = seat;
        this.baggage = baggage;
        this.booking = booking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Baggage getBaggage() {
        return baggage;
    }

    public void setBaggage(Baggage baggage) {
        this.baggage = baggage;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
