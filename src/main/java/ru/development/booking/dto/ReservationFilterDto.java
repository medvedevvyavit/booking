package ru.development.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReservationFilterDto {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
