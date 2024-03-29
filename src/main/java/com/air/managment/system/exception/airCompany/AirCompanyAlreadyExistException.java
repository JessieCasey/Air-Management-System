package com.air.managment.system.exception.airCompany;

import com.air.managment.system.exception.EntityAlreadyExistException;

public class AirCompanyAlreadyExistException extends EntityAlreadyExistException {
    public AirCompanyAlreadyExistException(Long id) {
        super("Air company with given id[" + id + "] already exists");
    }
}