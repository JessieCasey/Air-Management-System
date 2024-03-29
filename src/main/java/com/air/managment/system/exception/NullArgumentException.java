package com.air.managment.system.exception;

public class NullArgumentException extends IllegalArgumentException {
    public NullArgumentException(String argumentName) {
        super(argumentName + " cannot be null");
    }
}
