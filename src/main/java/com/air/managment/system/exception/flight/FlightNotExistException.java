package com.air.managment.system.exception.flight;

import com.air.managment.system.exception.EntityNotExistException;

public class FlightNotExistException extends EntityNotExistException {
    public FlightNotExistException(Long id) {
        super("No such flight found with given id[" + id + "]");
    }

}