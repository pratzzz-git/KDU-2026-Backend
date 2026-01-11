package com.company.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Annotation can be applied ONLY on fields
@Target(ElementType.FIELD)

// Annotation must be available at runtime
@Retention(RetentionPolicy.RUNTIME)
public @interface RangeCheck {

    int min(); // minimum allowed value

    int max(); // maximum allowed value
}