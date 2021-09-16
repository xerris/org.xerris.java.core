package org.xerris.core.validation;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class DatePredicates {
    public static Predicate<LocalDateTime> NotNull = Objects::nonNull;
    public static BiPredicate<LocalDateTime, LocalDateTime> Equals = Objects::equals;
    public static BiPredicate<LocalDateTime, LocalDateTime> NotEqual = (LocalDateTime one, LocalDateTime two) -> !Objects.equals(one, two);
    public static BiPredicate<LocalDateTime, LocalDateTime> LessThan = (value, target) -> value.compareTo(target) < 0;
    public static BiPredicate<LocalDateTime, LocalDateTime> GreaterThan = (value, target) -> NotNull.test(value) && value.compareTo(target) > 0;
    public static BiPredicate<LocalDateTime, LocalDateTime> LessThanEqual = (value, target) -> NotNull.test(value) && value.compareTo(target) <= 0;
    public static BiPredicate<LocalDateTime, LocalDateTime> GreaterThanEqual = (value, target) -> NotNull.test(value) && value.compareTo(target) >= 0;
}
