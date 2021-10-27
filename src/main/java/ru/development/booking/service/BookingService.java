package ru.development.booking.service;

import ru.development.booking.dto.BookingResourceDto;
import ru.development.booking.dto.ReservationDto;
import ru.development.booking.dto.ReservationFilterDto;
import ru.development.booking.model.ReservationId;

import java.util.List;

public interface BookingService {

    ReservationDto getReservationById(ReservationId id);
    BookingResourceDto getResourceById(String id);
    List<BookingResourceDto> getAllResources();
    List<ReservationDto> searchReservations(ReservationFilterDto filter);
    ReservationId saveReservation(ReservationDto reservation);
    ReservationDto updateReservation(ReservationDto reservation);
    String saveResource(BookingResourceDto resource);
}
