package com.internship.air_tickets_system.controllers;

import com.internship.air_tickets_system.models.Flight;
import com.internship.air_tickets_system.repositories.FlightsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
public class FlightsController {

    private final FlightsRepository flightsRepository;

    @Autowired
    public FlightsController(FlightsRepository flightsRepository) {
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
    // Parse the date without time
    LocalDate lahkumiseKuupaev = LocalDate.parse(LahkumiseAeg, DateTimeFormatter.ISO_DATE);

    List<Flight> flightsList = StreamSupport.stream(flightsRepository.findAll().spliterator(), false)
                                             .collect(Collectors.toList());

    return flightsList.stream()
        .filter(s -> s.getSaabumiskoht().equalsIgnoreCase(Saabumiskoht)
                && s.getSihtkoht().equalsIgnoreCase(Sihtkoht)
                && s.getLahkumiseaeg().toLocalDate().isEqual(lahkumiseKuupaev) 
                && s.getIstmed() >= Arv
                && (Hind == null || s.getHind() <= Hind)) 
        .collect(Collectors.toList());
}

    @GetMapping("/flights/{flightNumber}")
    public Flight getFlightByNumber(@PathVariable String flightNumber) {
        return flightsRepository.findById(flightNumber).orElse(null);
    }

    @PostMapping("/add")
    public @ResponseBody String addNewFlight(
        @RequestParam String saabumiskoht,
        @RequestParam String sihtkoht,
        @RequestParam String saabumiskohtcode,
        @RequestParam String sihtkohtcode,
        @RequestParam LocalDateTime lahkumiseaeg,
        @RequestParam LocalDateTime saabumiseaeg,
        @RequestParam double hind,
        @RequestParam int istmed
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
        flight.setIstmed(istmed);

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
