package com.internship.air_tickets_system.controllers;

import com.internship.air_tickets_system.models.Seat;
import com.internship.air_tickets_system.models.Passenger;
import com.internship.air_tickets_system.models.PassengerBooking;
import com.internship.air_tickets_system.repositories.SeatRepository;
import com.internship.air_tickets_system.repositories.PassengerBookingRepository;
import com.internship.air_tickets_system.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;



import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/passenger-booking")
public class PassengerBookingController {
    @Autowired
    private PassengerBookingRepository passengerBookingRepository;
    @Autowired
    private PassengerRepository passengerRepository;

    public static class PassengerRequest {

        private String reisijaENimi;
        
        private String reisijaPNimi;

        private LocalDate synniP;

        private String telefon;

        private String email;

        private String sugu;

        private String countryId;

        private String nationalId;

   


        public String getReisijaENimi() {
            return reisijaENimi;
        }

        public void setReisijaENimi(String reisijaENimi) {
            this.reisijaENimi = reisijaENimi;
        }

        public String getReisijaPNimi() {
            return reisijaPNimi;
        }

        public void setReisijaPNimi(String reisijaPNimi) {
            this.reisijaPNimi = reisijaPNimi;
        }

        public LocalDate getSynniP() {
            return synniP;
        }

        public void setSynniP(LocalDate synniP) {
            this.synniP = synniP;
        }

        public String getTelefon() {
            return telefon;
        }

        public void setTelefon(String telefon) {
            this.telefon = telefon;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSugu() {
            return sugu;
        }

        public void setSugu(String sugu) {
            this.sugu = sugu;
        }

        public String getCountryId() {
            return countryId;
        }

        public void setCountryId(String countryId) {
            this.countryId = countryId;
        }

        public String getNationalId() {
            return nationalId;
        }

        public void setNationalId(String nationalId) {
            this.nationalId = nationalId;
        }
    };


    @PostMapping("/create")
    public ResponseEntity<List<PassengerBooking>> createSeats(@RequestBody PassengerRequest passengerRequest) {
        System.out.println(passengerRequest.getCountryId());
        Passenger passenger = new Passenger();
        passenger.setReisijaENimi(passengerRequest.getReisijaENimi());
        passenger.setReisijaPNimi(passengerRequest.getReisijaPNimi());
        passenger.setSynniP(passengerRequest.getSynniP());
        passenger.setTelefon(passengerRequest.getTelefon());
        passenger.setEmail(passengerRequest.getEmail());
        passenger.setSugu(passengerRequest.getSugu());
        passenger.setCountryId(passengerRequest.getCountryId());
        passenger.setNationalIdHash(passengerRequest.getNationalId());
        System.out.println(passenger);

        passengerRepository.save(passenger);
        // boolean isSeat = seatRepository.findById(passenger.).orElse(null);
        // Flight flight = new Flight();
        // flight.setFlightString(flightString);
        // flight.setSaabumiskoht(saabumiskoht);
        // flight.setSihtkoht(sihtkoht);
        // flight.setSaabumiskohtcode(saabumiskohtcode);
        // flight.setSihtkohtcode(sihtkohtcode);
        // flight.setLahkumiseaeg(lahkumiseaeg);
        // flight.setSaabumiseaeg(saabumiseaeg);
        // flight.setHind(hind);

        // flightsRepository.save(flight);
        
        // Flight flight = Passenger.findByName(id).orElse(null);
        
        // if (flight == null) {
        //     throw new RuntimeException("Flight not found!");
        // }

        // for (int i = 1; i <= 20; i++) { 
        //     Seat seat = new Seat(i, false, false, 1, flight, true); 
        //     seatRepository.save(seat);
        // }
        
        return ResponseEntity.ok().body(passengerBookingRepository.findAll()); 
    }
}
