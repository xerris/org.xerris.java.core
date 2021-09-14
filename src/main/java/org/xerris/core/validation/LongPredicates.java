package org.xerris.core.validation;

import java.util.function.BiPredicate;

public class LongPredicates {
    public static BiPredicate<Long, Long> Equals = (value, target) -> value.compareTo(target) == 0;
    public static BiPredicate<Long, Long> NotEqual = (value, target) -> value.compareTo(target) != 0;
    public static BiPredicate<Long, Long> LessThan = (value, target) -> value.compareTo(target) < 0;
    public static BiPredicate<Long, Long> GreaterThan = (value, target) -> value.compareTo(target) > 0;
    public static BiPredicate<Long, Long> LessThanEqual = (value, target) -> value.compareTo(target) <= 0;
    public static BiPredicate<Long, Long> GreaterThanEqual = (value, target) -> value.compareTo(target) >= 0;
}
