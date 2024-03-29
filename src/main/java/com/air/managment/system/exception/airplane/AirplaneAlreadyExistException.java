package com.air.managment.system.exception.airplane;

import com.air.managment.system.exception.EntityAlreadyExistException;

public class AirplaneAlreadyExistException extends EntityAlreadyExistException {
    public AirplaneAlreadyExistException(Long id) {
        super("Airplane with given id[" + id + "] already exists");
    }
}