package com.example.ecommerce.ecommerce.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class IsInValidator implements ConstraintValidator<IsIn, String> {
    private String[] value;

    @Override
    public void initialize(IsIn constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String fieldValue, ConstraintValidatorContext context) {
        if (fieldValue != null) {
            return Arrays.asList(this.value).contains(fieldValue);
        }
        return true;
    }
}
