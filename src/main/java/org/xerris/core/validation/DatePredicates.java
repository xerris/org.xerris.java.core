package org.xerris.core.validation;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import org.joda.time.LocalDate;

public class DatePredicates {
    public static Predicate<LocalDate> NotNull = Objects::nonNull;
    public static BiPredicate<LocalDate, LocalDate> LessThan = (value, target) -> value.compareTo(target) < 0;
    public static BiPredicate<LocalDate, LocalDate> GreaterThan = (value, target) -> value.compareTo(target) > 0;
    public static BiPredicate<LocalDate, LocalDate> LessThanEqual = (value, target) -> value.compareTo(target) <= 0;
    public static BiPredicate<LocalDate, LocalDate> GreaterThanEqual = (value, target) -> value.compareTo(target) >= 0;
}
