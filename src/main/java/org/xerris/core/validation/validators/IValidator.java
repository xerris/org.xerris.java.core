package org.xerris.core.validation.validators;

import org.xerris.core.validation.Validate;

public interface IValidator<T> {
    void setSubject(T subject);
    Validate notNull(String message);
    Validate equalTo(T comparator, String message);
    Validate notEqualTo(T comparator, String message);
}
