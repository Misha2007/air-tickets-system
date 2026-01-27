package com.internship.air_tickets_system.models;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    public PassengerBooking(Long id, Passenger passenger, Seat seat, Baggage baggage, Booking booking) {
        this.id = id;
        this.passenger = passenger;
        this.seat = seat;
        this.baggage = baggage;
        this.booking = booking;
    }

    public PassengerBooking() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}