package ru.development.booking.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import ru.development.booking.dto.ReservationDto;
import ru.development.booking.model.Reservation;

import java.util.List;

@Slf4j
public class Writer implements ItemWriter<ReservationDto> {

    @Override
    public void write(List<? extends ReservationDto> reservations) {
        log.info(reservations.toString());
    }
}

