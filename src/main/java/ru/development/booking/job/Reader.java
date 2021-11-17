package ru.development.booking.job;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;
import ru.development.booking.dto.ReservationDto;
import ru.development.booking.dto.ReservationFilterDto;
import ru.development.booking.service.BookingService;

import java.time.LocalDateTime;
import java.util.Iterator;

public class Reader implements ItemReader<ReservationDto> {

    private final Iterator<ReservationDto> reservationIterator;

    public Reader(BookingService bookingService) {
        reservationIterator = bookingService
                .searchReservations(new ReservationFilterDto(
                        LocalDateTime.of(1900, 1, 1, 0, 0),
                        LocalDateTime.now().minusDays(1)
                )).iterator();
    }

    @Override
    public ReservationDto read() {
        if (reservationIterator.hasNext()){
            return reservationIterator.next();
        }
        return null;
    }
}
