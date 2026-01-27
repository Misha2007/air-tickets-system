package com.internship.air_tickets_system.controllers;

import com.internship.air_tickets_system.models.Flight;
import com.internship.air_tickets_system.repositories.FlightRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin()
@Controller
public class FlightsController {

    private final FlightRepository flightsRepository;

    @Autowired
    public FlightsController(FlightRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }

    @GetMapping("/flights")
    public @ResponseBody List<Flight> getFlights() {
        return StreamSupport.stream(flightsRepository.findAll().spliterator(), false)
                            .collect(Collectors.toList());
    }


    @GetMapping("/flights/filtered")
@ResponseBody
public List<Flight> getFilteredFlights(@RequestParam String Saabumiskoht, 
                                        @RequestParam String Sihtkoht, 
                                        @RequestParam String LahkumiseAeg, 
                                        @RequestParam int Arv, 
                                        @RequestParam(required = false) Double Hind) {
    LocalDate lahkumiseKuupaev = LocalDate.parse(LahkumiseAeg, DateTimeFormatter.ISO_DATE);

    List<Flight> flightsList = StreamSupport.stream(flightsRepository.findAll().spliterator(), false)
                                             .collect(Collectors.toList());

    return flightsList.stream()
        .filter(s -> s.getSaabumiskoht().equalsIgnoreCase(Saabumiskoht)
                && s.getSihtkoht().equalsIgnoreCase(Sihtkoht)
                && s.getLahkumiseaeg().toLocalDate().isEqual(lahkumiseKuupaev) 
                && s.getIstmed().size() >= Arv
                && (Hind == null || s.getHind() <= Hind)) 
        .collect(Collectors.toList());
}

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> getFlightByNumber(@PathVariable Long id) {
        
        return ResponseEntity.ok().body(flightsRepository.findById(id).orElse(null));
    }


    @GetMapping("/flights/date") 
    public ResponseEntity<List<Flight>> getFlightsDates(@PathVariable String datetimeStr) {
        // return ResponseEntity.ok().body(flightsRepository.findAllByAttribute(LocalDateTime datetime).orElse(null));
        LocalDateTime datetime = LocalDateTime.parse(datetimeStr);
        List<Flight> flights = flightsRepository.findByDepartureTime(datetime);
        return ResponseEntity.ok(flights);
        // @Query("SELECT ")
        // return ResponseEntity.ok().body(flightsRepository.findAllByAttribute(LocalDateTime datetime).orElse(null));
    }


    // @GetMapping("/flights/from") 
    // public ResponseEntity<List<Flight>> getFlightsDates(@PathVariable String datetimeStr) {
    //     // return ResponseEntity.ok().body(flightsRepository.findAllByAttribute(LocalDateTime datetime).orElse(null));
    //     System.out.println("Looking for the going flights");
    //     LocalDateTime datetime = LocalDateTime.parse(datetimeStr);
    //     List<Flight> flights = flightsRepository.findByDepartureTime(datetime);
    //     return ResponseEntity.ok(flights);
    //     // @Query("SELECT ")
    //     // return ResponseEntity.ok().body(flightsRepository.findAllByAttribute(LocalDateTime datetime).orElse(null));
    // }

    // @GetMapping("/from")
    // public ResponseEntity<List<City>> createSeats() {
    //     System.out.println("Looking for all cities");
        
    //     return ResponseEntity.ok().body(cityRepository.findAll()); 
    // }

    //  @GetMapping("/flights/airport/{saabumislennujid}") 
    // public ResponseEntity<List<Flight>> getFlightsByAirport(@PathVariable Long saabumislennujid) {
    //     System.out.println("Looking for flight with saabumislennujid: " + saabumislennujid);
    //     List<Flight> flights = flightsRepository.findBysaabumislennuJId_Id(saabumislennujid);
    //     return ResponseEntity.ok(flights);
    // }

    @PostMapping("/add")
    public @ResponseBody String addNewFlight(
        @RequestParam String saabumiskoht,
        @RequestParam String sihtkoht,
        @RequestParam String saabumiskohtcode,
        @RequestParam String sihtkohtcode,
        @RequestParam LocalDateTime lahkumiseaeg,
        @RequestParam LocalDateTime saabumiseaeg,
        @RequestParam double hind
    ) {
        String flightNumber = generateFlightNumber();

        Flight flight = new Flight();
        flight.setFlightnumber(flightNumber);
        flight.setSaabumiskoht(saabumiskoht);
        flight.setSihtkoht(sihtkoht);
        flight.setSaabumiskohtcode(saabumiskohtcode);
        flight.setSihtkohtcode(sihtkohtcode);
        flight.setLahkumiseaeg(lahkumiseaeg);
        flight.setSaabumiseaeg(saabumiseaeg);
        flight.setHind(hind);

        flightsRepository.save(flight);
        return "Saved";
    }

    private String generateFlightNumber() {
        Random random = new Random();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder flightCode = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            flightCode.append(letters.charAt(random.nextInt(letters.length())));
        }

        for (int i = 0; i < 3; i++) {
            flightCode.append(random.nextInt(10));
        }

        return flightCode.toString();
    }
}
