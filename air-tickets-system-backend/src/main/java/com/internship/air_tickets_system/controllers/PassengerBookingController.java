package com.internship.air_tickets_system.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internship.air_tickets_system.models.Baggage;
import com.internship.air_tickets_system.models.Booking;
import com.internship.air_tickets_system.models.Passenger;
import com.internship.air_tickets_system.models.PassengerBooking;
import com.internship.air_tickets_system.models.Seat;
import com.internship.air_tickets_system.repositories.BaggageRepository;
import com.internship.air_tickets_system.repositories.BookingRepository;
import com.internship.air_tickets_system.repositories.PassengerBookingRepository;
import com.internship.air_tickets_system.repositories.PassengerRepository;
import com.internship.air_tickets_system.repositories.SeatRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
    name = "Passenger Booking",
    description = "Create passengers and assign seats, baggage, and bookings"
)
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/passenger-booking")
public class PassengerBookingController {
    @Autowired
    private PassengerBookingRepository passengerBookingRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private BaggageRepository baggageRepository;

    @Schema(description = "Passenger creation request")
    public static class PassengerRequest {

        @Schema(example = "John")
        private String reisijaENimi;

        @Schema(example = "Doe")
        private String reisijaPNimi;

        @Schema(example = "1995-06-15", type = "string", format = "date")
        private LocalDate synniP;

        @Schema(example = "+3725555555")
        private String telefon;

        @Schema(example = "john.doe@email.com")
        private String email;

        @Schema(example = "M")
        private String sugu;

        @Schema(example = "EE")
        private String countryId;

        @Schema(example = "1234567890")
        private String nationalId;

        @Schema(description = "Seat ID to assign", example = "12")
        private Long seatId;

        @Schema(description = "Baggage option ID", example = "2")
        private Long baggageId;

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

        public Long getSeatId() {
            return seatId;
        }

        public void setSeatId(Long seatId) {
            this.seatId = seatId;
        }

        public Long getBaggageId() {
            return baggageId;
        }

        public void setBaggageId(Long baggageId) {
            this.baggageId = baggageId;
        }
    };

    @Operation(
        summary = "Create passenger booking",
        description = """
            Creates a new booking and assigns:
            - passengers
            - seats
            - baggage
            
            One booking is created for all passengers in the request.
            """
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Passenger booking created successfully",
            content = @Content(array = @ArraySchema(
                schema = @Schema(implementation = PassengerBooking.class)
            ))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "404", description = "Seat or baggage not found")
    })
    @PostMapping("/create")
    public ResponseEntity<List<PassengerBooking>> createSeats(@RequestBody List<PassengerRequest> passengerRequests) {
        Booking booking = new Booking();
        bookingRepository.save(booking);
        for (PassengerRequest passengerRequest : passengerRequests) {
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


            PassengerBooking passengerBooking = new PassengerBooking();
            passengerBooking.setPassenger(passenger);

            Seat seat = seatRepository.findById(passengerRequest.getSeatId())
                    .orElseThrow(() -> new RuntimeException("Seat not found"));
            passengerBooking.setSeat(seat);

            seat.setVaba(false);

            seatRepository.save(seat);

            Baggage baggage = baggageRepository.findById(passengerRequest.getBaggageId())
                    .orElseThrow(() -> new RuntimeException("Baggage not found"));
            passengerBookingRepository.save(passengerBooking);

            passengerRepository.save(passenger);
            passengerBooking.setSeat(seat);
            passengerBooking.setBaggage(baggage);
            passengerBooking.setBooking(booking);

            passengerBookingRepository.save(passengerBooking);
        }

        List<PassengerBooking> allBookings = passengerBookingRepository.findByBookingId(booking.getId());
        return ResponseEntity.ok(allBookings);
    }
}
