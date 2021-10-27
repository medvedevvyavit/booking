package ru.development.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.development.booking.dto.ReservationDto;
import ru.development.booking.dto.ReservationFilterDto;
import ru.development.booking.model.ReservationId;
import ru.development.booking.service.BookingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking/reservations")
public class ReservationController {

    private final BookingService service;

    @GetMapping("{resourceId}/{bookingNumber}")
    public ResponseEntity<ReservationDto> getReservation(@PathVariable String resourceId, @PathVariable String bookingNumber) {
        ReservationDto reservationDto = service.getReservationById(new ReservationId(resourceId, bookingNumber));
        return new ResponseEntity<>(reservationDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ReservationDto>> searchReservations(ReservationFilterDto filter){
        return new ResponseEntity<>(service.searchReservations(filter), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ReservationId> saveReservation(@RequestBody ReservationDto reservation){
        return new ResponseEntity<>(service.saveReservation(reservation), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ReservationDto> updateReservation(@RequestBody ReservationDto reservation) {
        return new ResponseEntity<>(service.updateReservation(reservation), HttpStatus.OK);
    }
}