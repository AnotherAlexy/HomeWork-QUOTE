package com.testproject.stockservice.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = BidShouldBeLessThanAskConstraintValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
@Documented
public @interface BidShouldBeLessThanAskConstraint {
    String message() default
            "Bid should be less than ask";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
