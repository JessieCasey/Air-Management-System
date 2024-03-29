package com.air.managment.system.common.validator.trimmed;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TrimmedValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Trimmed {
    String message() default "Value must be trimmed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
