package com.example.ecommerce.ecommerce.validator;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsInValidator.class)
@Documented
public @interface IsIn {
    String[] value() default "";
    String message() default "";
}
