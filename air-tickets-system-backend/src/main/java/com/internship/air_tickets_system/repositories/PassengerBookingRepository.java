package com.internship.air_tickets_system.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internship.air_tickets_system.models.PassengerBooking;
public interface PassengerBookingRepository extends JpaRepository<PassengerBooking, Long> {
    
    List<PassengerBooking> findByBookingId(Long saabumislennuJId);

}