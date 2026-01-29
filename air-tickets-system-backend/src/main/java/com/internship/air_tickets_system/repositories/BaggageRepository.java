package com.internship.air_tickets_system.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.internship.air_tickets_system.models.Baggage;
public interface BaggageRepository extends JpaRepository<Baggage, Long> {
}