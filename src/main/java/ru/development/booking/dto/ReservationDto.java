package ru.development.booking.dto;

import lombok.Data;
import ru.development.booking.model.ReservationId;
import ru.development.booking.model.ReservationStatus;

import java.time.LocalDateTime;

@Data
public class ReservationDto {

    private ReservationId id;
    private String bookingNumber;
    private String creatorName;
    private LocalDateTime createDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer personNumber;
    private ReservationStatus status;
}
