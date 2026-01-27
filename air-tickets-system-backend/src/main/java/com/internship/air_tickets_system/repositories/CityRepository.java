package com.internship.air_tickets_system.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.internship.air_tickets_system.models.City;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City, Long> {


    // @Query("SELECT * FROM Flight WHERE saabumiskoht")
    // List<City> findByDepartureTime(@Param("datetime") LocalDateTime datetime);

    // @Query("SELECT c FROM City c WHERE c.linna_nimi LIKE %:text%")
    // List<City> findAllMatchesByText(@Param("text") String text);

    List<City> findBylinnaNimiContainingIgnoreCase(String text);

}

