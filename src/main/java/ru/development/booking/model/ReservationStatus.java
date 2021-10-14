package ru.development.booking.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ReservationStatus {
    NEW("1"),
    DRAFT("2"),
    DELETED("3");

    private final String code;
}
