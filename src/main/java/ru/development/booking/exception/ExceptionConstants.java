package ru.development.booking.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionConstants {

    public static final String ENTITY_ALREADY_EXISTS_MSG = "Entity: %s already exists";
    public static final String ENTITY_ALREADY_EXISTS_CROSSING_DATE_MSG = "Entity: %s already exists on crossing date";
}
