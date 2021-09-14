package org.xerris.core.validation;

import java.util.function.BiPredicate;

public class IntegerPredicates {
    public static BiPredicate<Integer, Integer> Equals = (value, target) -> value.compareTo(target) == 0;
    public static BiPredicate<Integer, Integer> NotEqual = (value, target) -> value.compareTo(target) != 0;
    public static BiPredicate<Integer, Integer> LessThan = (value, target) -> value.compareTo(target) < 0;
    public static BiPredicate<Integer, Integer> GreaterThan = (value, target) -> value.compareTo(target) > 0;
    public static BiPredicate<Integer, Integer> LessThanEqual = (value, target) -> value.compareTo(target) <= 0;
    public static BiPredicate<Integer, Integer> GreaterThanEqual = (value, target) -> value.compareTo(target) >= 0;
}

