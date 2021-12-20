package ru.development.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.development.booking.dto.BookingResourceDto;
import ru.development.booking.service.BookingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking/resources")
public class ResourceController {

    private final BookingService service;

    @GetMapping("{id}")
    public ResponseEntity<BookingResourceDto> getResource(@PathVariable String id) {
        return new ResponseEntity<>(service.getResourceById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookingResourceDto>> getAllResources(){
        return new ResponseEntity<>(service.getAllResources(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_RESERVATION')")
    public ResponseEntity<String> saveResource(@RequestBody BookingResourceDto resource){
        return new ResponseEntity<>(service.saveResource(resource), HttpStatus.OK);
    }
}
