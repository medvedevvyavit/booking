package ru.development.booking.mapper;

import org.mapstruct.Mapper;
import ru.development.booking.dto.BookingResourceDto;
import ru.development.booking.dto.ReservationDto;
import ru.development.booking.model.BookingResource;
import ru.development.booking.model.Reservation;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    ReservationDto toReservationDto(Reservation source);
    Reservation toReservation(ReservationDto source);
    BookingResourceDto toBookingResourceDto(BookingResource source);
}
