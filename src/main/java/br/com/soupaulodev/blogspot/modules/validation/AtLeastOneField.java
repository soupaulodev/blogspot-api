package br.com.soupaulodev.blogspot.modules.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AtLeastOneFieldValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AtLeastOneField {
    String message() default "At least one field must be filled in";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
