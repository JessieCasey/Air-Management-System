package com.air.managment.system.common.validator.trimmed;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TrimmedValidator implements ConstraintValidator<Trimmed, String> {

    @Override
    public void initialize(Trimmed constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || value.trim().equals(value);
    }
}
