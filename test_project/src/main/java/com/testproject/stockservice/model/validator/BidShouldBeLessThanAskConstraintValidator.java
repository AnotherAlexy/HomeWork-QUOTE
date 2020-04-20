package com.testproject.stockservice.model.validator;

import com.testproject.stockservice.model.dto.QuoteDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
public class BidShouldBeLessThanAskConstraintValidator implements ConstraintValidator<BidShouldBeLessThanAskConstraint, QuoteDto> {
    @Override
    public boolean isValid(QuoteDto value, ConstraintValidatorContext context) {
        if (value.getAsk() == null || value.getBid() == null) {
            return true;
        }
        return value.getBid() < value.getAsk();
    }
}
