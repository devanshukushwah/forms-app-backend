package com.formsapp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  // Apply only to methods
@Retention(RetentionPolicy.RUNTIME)  // Retain at runtime for reflection
public @interface FormEditPermissionAnnotation {
    String value() default "";  // Optional value
}
