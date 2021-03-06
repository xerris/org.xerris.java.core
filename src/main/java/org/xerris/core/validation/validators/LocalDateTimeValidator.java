package org.xerris.core.validation.validators;

import org.xerris.core.validation.Validate;
import java.time.LocalDateTime;
import java.util.function.BiPredicate;

public class LocalDateTimeValidator extends BaseChronoLocalDateValidator<LocalDateTime> {

    public LocalDateTimeValidator(Validate validate) {
        super(validate);
    }

    protected BiPredicate<LocalDateTime, LocalDateTime> equalTo() {
        return (value, comparator) -> value.compareTo(comparator) ==0;
    }

    public BiPredicate<LocalDateTime, LocalDateTime> greaterThan() {
        return (value, target) -> notNull.test(value) && value.compareTo(target) > 0;
    }

    public BiPredicate<LocalDateTime, LocalDateTime> greaterThanOrEqualTo() {
        return (value, target) -> notNull.test(value) && value.compareTo(target) >= 0;
    }

    public BiPredicate<LocalDateTime, LocalDateTime> lessThan() {
        return (value, target) -> value.compareTo(target) < 0;
    }

    public BiPredicate<LocalDateTime, LocalDateTime> lessThanOrEqualTo() {
        return (value, target) -> notNull.test(value) && value.compareTo(target) <= 0;
    }
}

