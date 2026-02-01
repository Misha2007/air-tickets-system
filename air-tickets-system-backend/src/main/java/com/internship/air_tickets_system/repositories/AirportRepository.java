package com.internship.air_tickets_system.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.internship.air_tickets_system.models.Airport;
public interface AirportRepository extends JpaRepository<Airport, Long> {
}