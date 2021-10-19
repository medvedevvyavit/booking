package ru.development.booking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationFilterDto {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
