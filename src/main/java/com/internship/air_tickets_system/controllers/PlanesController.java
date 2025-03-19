package com.internship.air_tickets_system.controllers;
import com.internship.air_tickets_system.models.Flights;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PlanesController {
    private Map<String, List<Flights>> flightMap = new HashMap<>();

    public PlanesController() {
        flightMap.put("1", List.of(new Flights("Finland", LocalDate.of(2025, 7, 19), 213, 30.29)));
        flightMap.put("2", List.of(new Flights("Germany", LocalDate.of(2025, 8, 25), 180, 100.5)));
    }

    private List<Flights> db = List.of(
        new Flights("Finland", LocalDate.of(2025, 7, 19), 213, 30.29),
        new Flights("Germany", LocalDate.of(2025, 8, 25), 180, 100.5)
    );

    @GetMapping("/flights")
    public List<Flights> getFlights() {
        return db;
    }

    @GetMapping("/flights/{Sihtkoht}")
    public List<Flights> getFlights(@PathVariable String Sihtkoht) {
        System.out.println(db.stream().toList());
        return db.stream().filter(s -> s.getSihtkoht().equalsIgnoreCase(Sihtkoht) ).toList();
    }
}
