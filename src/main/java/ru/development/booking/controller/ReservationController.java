package ru.development.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping
    public ResponseEntity<List<ReservationDto>> searchReservations(ReservationFilterDto filter){
        return new ResponseEntity<>(service.searchReservations(filter), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_RESERVATION')")
    public ResponseEntity<ReservationId> saveReservation(@RequestBody ReservationDto reservation){
        return new ResponseEntity<>(service.saveReservation(reservation), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ReservationDto> updateReservation(@RequestBody ReservationDto reservation) {
        return new ResponseEntity<>(service.updateReservation(reservation), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReservationDto>> getAllReservations(){
        return new ResponseEntity<>(service.getAllReservations(), HttpStatus.OK);
    }
}
