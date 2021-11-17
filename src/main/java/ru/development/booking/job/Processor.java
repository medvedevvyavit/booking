package ru.development.booking.job;

import org.springframework.batch.item.ItemProcessor;
import ru.development.booking.dto.ReservationDto;
import ru.development.booking.model.Reservation;

public class Processor implements ItemProcessor<ReservationDto, ReservationDto> {

    @Override
    public ReservationDto process(ReservationDto reservation) {
        return reservation;
    }
}
