package com.air.managment.system.exception.airplane;

import com.air.managment.system.exception.EntityNotExistException;

public class AirplaneNotExistException extends EntityNotExistException {
    public AirplaneNotExistException(Long id) {
        super("No such airplane found with given id[" + id + "]");
    }

}