package ru.development.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.development.booking.dto.BookingResourceDto;
import ru.development.booking.dto.ReservationDto;
import ru.development.booking.model.ReservationId;
import ru.development.booking.service.BookingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private final BookingService service;

    @GetMapping("/reservations/{resourceId}/{bookingNumber}")
    public ResponseEntity<ReservationDto> getReservation(@PathVariable String resourceId, @PathVariable String bookingNumber) {
        ReservationDto reservationDto = service.getReservationById(new ReservationId(resourceId, bookingNumber));
        return new ResponseEntity<>(reservationDto, HttpStatus.OK);
    }

    @GetMapping("/resources/{id}")
    public ResponseEntity<BookingResourceDto> getResource(@PathVariable String id) {
        return new ResponseEntity<>(service.getResourceById(id), HttpStatus.OK);
    }

    @GetMapping("/allResources")
    public ResponseEntity<List<BookingResourceDto>> getAllResources(){
        return new ResponseEntity<>(service.getAllResources(), HttpStatus.OK);
    }
}
