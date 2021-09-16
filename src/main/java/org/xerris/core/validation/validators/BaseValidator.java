package org.xerris.core.validation.validators;

import org.xerris.core.validation.Validate;

public abstract class BaseValidator<T> implements IValidator<T> {
    private final Validate validate;
    private T subject;

    protected BaseValidator(Validate validate) {
        this.validate = validate;
    }

    @Override
    public void setSubject(T subject) {
        this.subject = subject;
    }

    protected Validate validate() { return validate; }
    protected T subject() { return subject; }
}
