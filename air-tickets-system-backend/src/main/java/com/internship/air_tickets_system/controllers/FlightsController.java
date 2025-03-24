package com.internship.air_tickets_system.controllers;
import com.internship.air_tickets_system.models.Flights;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FlightsController {
    private Map<String, List<Flights>> flightMap = new HashMap<>();

    public FlightsController() {
        flightMap.put("1", List.of(new Flights(0, "Estonia", "Finland", LocalDate.of(2025, 7, 19), 213, 30.29, 30)));
        flightMap.put("2", List.of(new Flights(1, "Austria", "Germany", LocalDate.of(2025, 8, 25), 180, 100.5, 30)));
    }

    private List<Flights> db = List.of(
        new Flights(0, "Estonia", "Finland", LocalDate.of(2025, 7, 19), 213, 30.29, 30),
        new Flights(1, "Austria", "Germany", LocalDate.of(2025, 8, 25), 180, 100.5, 30)
    );

    @GetMapping("/flights")
    public List<Flights> getFlights() {
        return db;
    }

    @GetMapping("/flights/filtered")
    @ResponseBody
    public List<Flights> getFilteredFlights(@RequestParam String Saabumiskoht, @RequestParam String Sihtkoht, @RequestParam String Kuupaev, @RequestParam double Hind, @RequestParam int Arv) {
        LocalDate kuupaev = LocalDate.parse(Kuupaev);
        if ("anywhere".equalsIgnoreCase(Sihtkoht)) {
            return db.stream()
            .filter(s -> s.getSaabumiskoht().equalsIgnoreCase(Saabumiskoht) 
            && s.getKuupaev().equals(kuupaev) 
            && s.getIstmed() > (Arv) 
            && s.getHind() < (Hind))
            .toList();
        } else{
            return db.stream()
            .filter(s -> s.getSaabumiskoht().equalsIgnoreCase(Saabumiskoht) 
            && s.getSihtkoht().equalsIgnoreCase(Sihtkoht) 
            && s.getKuupaev().equals(kuupaev)
            && s.getIstmed() > (Arv) 
            &&s.getHind() < (Hind))
            .toList();
        }
    }

    @GetMapping("/flights/{id}")
    public List<Flights> getFlightById(@PathVariable int id) {
        return db.stream().filter(s -> s.getId() == id)
        .toList();
    }
}


