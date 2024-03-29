package com.air.managment.system.exception.airCompany;

import com.air.managment.system.exception.EntityNotExistException;

public class AirCompanyNotExistException extends EntityNotExistException {

    public AirCompanyNotExistException() {
        super("No such air company found");
    }

    public AirCompanyNotExistException(Long id) {
        super("No such air company found with given id[" + id + "]");
    }

    public AirCompanyNotExistException(String companyName) {
        super("No such air company found with given companyName[" + companyName + "]");
    }
}