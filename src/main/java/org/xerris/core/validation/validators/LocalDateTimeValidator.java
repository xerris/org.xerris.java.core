package org.xerris.core.validation.validators;

import org.xerris.core.validation.DatePredicates;
import org.xerris.core.validation.Validate;
import java.time.LocalDateTime;

public class LocalDateTimeValidator extends BaseValidator<LocalDateTime>  {

    public LocalDateTimeValidator(Validate validate) {
        super(validate);
    }

    public Validate Equal(LocalDateTime value, String message) {
        validate().add(DatePredicates.Equals, subject(), value, message);
        return validate();
    }
}
