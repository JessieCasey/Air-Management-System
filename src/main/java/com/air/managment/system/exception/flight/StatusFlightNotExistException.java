package com.air.managment.system.exception.flight;

import com.air.managment.system.exception.EntityNotExistException;
import com.air.managment.system.flight.FlightStatus;

import java.util.Arrays;

public class StatusFlightNotExistException extends EntityNotExistException {
    public StatusFlightNotExistException() {
        super("Status flight is not found, please use these: " + Arrays.toString(FlightStatus.values()));
    }

}