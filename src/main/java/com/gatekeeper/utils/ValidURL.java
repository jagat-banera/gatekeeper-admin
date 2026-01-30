package com.gatekeeper.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckURL.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidURL {

    String message() default "Invalid Target URL";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
