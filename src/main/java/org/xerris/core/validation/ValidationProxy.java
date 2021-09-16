package org.xerris.core.validation;

import java.time.LocalDateTime;

public class ValidationProxy {
    private final Validate validate;

    public ValidationProxy(Validate validate) {
        this.validate = validate;
    }

    public Validate notEmpty(String value, String message) {
        return validate.add(StringPredicates.NotEmpty, value, message);
    }

    public Validate NotNull(LocalDateTime date, String message) {
        return validate.add(DatePredicates.NotNull, date, message);
    }

    public Validate GreaterThan(LocalDateTime date, LocalDateTime  comparison, String message) {
        return validate.add(DatePredicates.GreaterThan, date, comparison, message);
    }
}
