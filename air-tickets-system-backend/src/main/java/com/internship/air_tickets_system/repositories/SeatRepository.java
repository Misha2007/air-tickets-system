package com.internship.air_tickets_system.repositories;

import com.internship.air_tickets_system.models.Flight;
import com.internship.air_tickets_system.models.Seat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByFlight(Flight flight); 
}
