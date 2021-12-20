package ru.development.booking.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionConstants {

    public static final String ENTITY_ALREADY_EXISTS_MSG = "Entity: %s by %s already exists";
    public static final String ENTITY_NOT_FOUND_MSG = "Entity: %s by %s not found";
    public static final String CREATE_RESERVATION_IN_THE_PAST = "It is not possible to create a reservation in the past time";
    public static final String ENTITY_ALREADY_EXISTS_CROSSING_DATE_MSG = "Entity: %s already exists on crossing date";
}
