package org.xerris.core.validation.validators;

import org.xerris.core.validation.StringPredicates;
import org.xerris.core.validation.Validate;

public class StringValidator extends BaseValidator<String> {

    public StringValidator(Validate validate) {
        super(validate);
    }

    public Validate notEmpty(String message) {
        validate().add(StringPredicates.NotEmpty, subject(), message);
        return validate();
    }
}
