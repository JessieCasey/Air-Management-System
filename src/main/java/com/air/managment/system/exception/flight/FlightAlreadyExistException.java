package com.air.managment.system.exception.flight;

import com.air.managment.system.exception.EntityAlreadyExistException;

public class FlightAlreadyExistException extends EntityAlreadyExistException {
    public FlightAlreadyExistException(Long id) {
        super("Flight with given id[" + id + "] already exists");
    }
}