package org.xerris.core.validation.validators;

public interface ICondition<T> {
    Boolean Test(T value);
}
