package org.xerris.core.validation.validators;

import org.xerris.core.validation.Validate;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public abstract class BaseValidator<T extends Comparable<T>> implements IValidator<T> {
    private final Validate validate;
    private T subject;

    protected BaseValidator(Validate validate) {
        this.validate = validate;
    }

    public void setSubject(T subject) {
        this.subject = subject;
    }

    protected T subject() {
        return subject;
    }

    protected Validate validate() {
        return validate;
    }

    protected Validate add(Predicate<T> predicate, String message) {
        return validate.add(predicate, subject, message);
    }

    protected Validate add(BiPredicate<T, T> predicate, T comparator, String message) {
        return validate.add(predicate, subject, comparator, message);
    }

    public Validate notNull(String message) {
        return add(notNull, message);
    }
    public Validate isNull(String message) {
        return add(isNull, message);
    }

    public Validate equalTo(T comparator, String message) {
        return add(equalTo, comparator, message);
    }

    public Validate notEqualTo(T comparator, String message) {
        return add(notEqualTo, comparator, message);
    }

    public Validate greaterThanOrEqualTo(T comparator, String message) {
        return add(greaterThanOrEqualTo, comparator, message);
    }

    public Validate lessThan(T comparator, String message) {
        return add(lessThan, comparator, message);
    }

    public Validate lessThanOrEqualTo(T comparator, String message) {
        return add(lessThanOrEqualTo, comparator, message);
    }

    private final Predicate<T> notNull = Objects::nonNull;
    private final Predicate<T> isNull = Objects::isNull;

    private final BiPredicate<T, T> equalTo = (T one, T two) -> {
        if (notNull.test(one) && notNull.test(two)) return one.equals(two);
        if (isNull.test(one) && isNull.test(two)) return true;
        return false;
    };
    private final BiPredicate<T, T> notEqualTo = (T one, T two) -> !equalTo.test(one, two);
    private final BiPredicate<T, T> greaterThan = (T one, T two) -> one.compareTo(two) > 0;
    private final BiPredicate<T, T> greaterThanOrEqualTo = (T one, T two) -> one.compareTo(two) >= 0;
    private final BiPredicate<T, T> lessThan = (T one, T two) -> one.compareTo(two) < 0;
    private final BiPredicate<T, T> lessThanOrEqualTo = (T one, T two) -> one.compareTo(two) <= 0;
}