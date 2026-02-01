package com.internship.air_tickets_system.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internship.air_tickets_system.models.City;

public interface CityRepository extends JpaRepository<City, Long> {


    // @Query("SELECT * FROM Flight WHERE saabumiskoht")
    // List<City> findByDepartureTime(@Param("datetime") LocalDateTime datetime);

    // @Query("SELECT c FROM City c WHERE c.linna_nimi LIKE %:text%")
    // List<City> findAllMatchesByText(@Param("text") String text);

    List<City> findBylinnaNimiContainingIgnoreCase(String text);

}

