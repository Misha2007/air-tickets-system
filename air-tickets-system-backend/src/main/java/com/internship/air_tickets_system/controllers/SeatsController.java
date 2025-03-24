package com.internship.air_tickets_system.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.internship.air_tickets_system.models.Seats;

@RestController
public class SeatsController {
    private Map<String, List<Seats>> seatMap = new HashMap<>();

    public SeatsController() {
        seatMap.put("1", List.of(new Seats(0, false, false, 1,0)));
        seatMap.put("2", List.of(new Seats(2, false, false, 1,0)));
    }

    private List<Seats> db = List.of(
        new Seats(0, false, false, 1,0),
        new Seats(2, false, false, 1,0)
    );

    @GetMapping("/flights/{id}/seats")
    public List<Seats> getAllSeatsByFlight(@PathVariable int id){
        return db.stream().filter(s -> s.getLennuId() == id)
        .toList();
    }
}
