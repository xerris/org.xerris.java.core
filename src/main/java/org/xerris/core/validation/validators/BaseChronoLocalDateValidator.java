package org.xerris.core.validation.validators;

import org.xerris.core.validation.Validate;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public abstract class BaseChronoLocalDateValidator<T> implements IValidator<T> {
    private final Validate validate;
    private T subject;

    protected BaseChronoLocalDateValidator(Validate validate) {
        this.validate = validate;
    }

    protected Validate validate() { return validate; }
    protected T subject() { return subject; }

    public void setSubject(T subject) { this.subject = subject; }

    protected abstract BiPredicate<T, T> equalTo();
    protected abstract BiPredicate<T, T> greaterThan();
    protected abstract BiPredicate<T, T> greaterThanOrEqualTo();
    protected abstract BiPredicate<T, T> lessThan();
    protected abstract BiPredicate<T, T> lessThanOrEqualTo();

    public Validate notNull(String message) { return add(notNull, message); }
    public Validate equalTo(T value, String message) { return add(equalTo(), subject, value, message); }
    public Validate notEqualTo(T value, String message) { return add(notEqualTo, subject, value, message); }

    public Validate greaterThan(T comparator, String message) {
        return add(greaterThan(), subject(), comparator, message);
    }

    public Validate greaterThanOrEqualTo(T comparator, String message) {
        return add(greaterThanOrEqualTo(), subject(), comparator, message);
    }

    public Validate lessThan(T comparator, String message) {
        return add(lessThan(), subject, comparator, message);
    }

    public Validate lessThanOrEqualTo(T comparator, String message) {
        return add(lessThanOrEqualTo(), subject, comparator, message);
    }

    protected Validate add(Predicate<T> predicate, String message) {
        validate.add(predicate, subject, message);
        return validate();
    }

    protected Validate add(BiPredicate<T, T> predicate, T subject, T comparator, String message) {
        validate.add(predicate, subject, comparator, message);
        return validate();
    }

    protected Predicate<T> notNull = Objects::nonNull;
    private BiPredicate<T,T> notEqualTo = (T one, T two) -> !equalTo().test(one, two);
}
