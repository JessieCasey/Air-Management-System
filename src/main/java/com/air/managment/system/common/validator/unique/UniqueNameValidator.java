package com.air.managment.system.common.validator.unique;

import com.air.managment.system.aircompany.repository.AirCompanyRepository;
import com.air.managment.system.airplane.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    private final ApplicationContext context;
    private Class<?> entityClass;

    @Autowired
    public UniqueNameValidator(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void initialize(UniqueName constraintAnnotation) {
        this.entityClass = constraintAnnotation.entityClass();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        if (name == null) {
            return false;
        }

        // Determine the repository based on the class type
        if (entityClass == null) {
            throw new IllegalStateException("Unable to determine entity class from ConstraintValidatorContext");
        }

        // Obtain the repository bean dynamically
        // The initial letter should be lowercase because beans created by Spring follow this convention (e.g. airplaneRepository, airCompanyRepository etc.)
        String repositoryBeanName = Character.toLowerCase(entityClass.getSimpleName().charAt(0)) + entityClass.getSimpleName().substring(1) + "Repository";

        Object repository = context.getBean(repositoryBeanName);
        // Check if the value exists in the repository
        if (repository instanceof AirCompanyRepository) {
            return !((AirCompanyRepository) repository).existsByName(name);
        } else if (repository instanceof AirplaneRepository) {
            return !((AirplaneRepository) repository).existsByFactorySerialNumber(name);
        }
        return false;
    }
}

