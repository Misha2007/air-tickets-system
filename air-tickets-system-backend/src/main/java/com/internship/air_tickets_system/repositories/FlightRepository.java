package com.internship.air_tickets_system.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.internship.air_tickets_system.models.Flight;
public interface FlightRepository extends JpaRepository<Flight, Long> {

    // @Query("SELECT f FROM flight f WHERE f.lahkumiseaeg = :datetime")
    // List<Flight> findByDepartureTime(@Param("datetime") LocalDateTime datetime);


    // Dont forget to add _Id to the end of method name, if you're using ID just findBysaabumislennuJID won't work you have to have it ending with _Id
    List<Flight> findBysaabumislennuJId_Id(Long saabumislennuJId);


    @Query(value = "SELECT DISTINCT f.saabumiskoht FROM flight f", nativeQuery = true)
    List<String> findDistinctSaabumiskoht();

    
    
    @Query(value = "SELECT f.sihtkoht FROM flight f WHERE f.saabumiskoht = :saabumiskoht", nativeQuery = true)
    List<String> findBySihtkoht(String saabumiskoht);

}

