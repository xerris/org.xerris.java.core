package org.xerris.core.validation.validators;

import org.xerris.core.validation.Validate;

import java.time.LocalDate;
import java.util.function.BiPredicate;

public class LocalDateValidator extends BaseChronoLocalDateValidator<LocalDate> {

    public LocalDateValidator(Validate validate) {
        super(validate);
    }

    protected BiPredicate<LocalDate, LocalDate> equalTo() {
        return (value, comparator) -> value.compareTo(comparator) ==0;
    }

    public BiPredicate<LocalDate, LocalDate> greaterThan() {
        return (value, target) -> notNull.test(value) && value.compareTo(target) > 0;
    }

    public BiPredicate<LocalDate, LocalDate> greaterThanOrEqualTo() {
        return (value, target) -> notNull.test(value) && value.compareTo(target) >= 0;
    }

    public BiPredicate<LocalDate, LocalDate> lessThan() {
        return (value, target) -> value.compareTo(target) < 0;
    }

    public BiPredicate<LocalDate, LocalDate> lessThanOrEqualTo() {
        return (value, target) -> notNull.test(value) && value.compareTo(target) <= 0;
    }
}