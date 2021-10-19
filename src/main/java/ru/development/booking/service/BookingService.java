package ru.development.booking.service;

import ru.development.booking.dto.BookingResourceDto;
import ru.development.booking.dto.ReservationDto;
import ru.development.booking.model.ReservationId;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    ReservationDto getReservationById(ReservationId id);
    BookingResourceDto getResourceById(String id);
    List<BookingResourceDto> getAllResources();
    List<ReservationDto> searchReservations(LocalDateTime startDate, LocalDateTime endDate);
    ReservationId saveReservation(ReservationDto reservation);
}
