package org.xerris.core.validation;

import java.util.Objects;
import java.util.function.Predicate;

public class StringPredicates {
    public static Predicate<Object> NotNull = Objects::nonNull;
    public static Predicate<String> NotEmpty = (String s) -> NotNull.test(s) && !s.isEmpty();
    public static Predicate<String> Email = (String s) -> s.matches("^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$");
    public static Predicate<String> Numeric = (String s) -> s.matches("^([-+]?[0-9]\\d*(\\.\\d+)?|[0]*\\.\\d\\d*)$");
    public static Predicate<String> Integer = (String s) -> s.matches("^([-+]?[0-9]\\d*)$");
    public static Predicate<String> PhoneNumber = (String s) -> s.matches("^(\\(?)[2-9](\\d{2})[- ]?([).\\s]?)[2-9](\\d{2})[- ]?([-.\\s]?)(\\d{4})(?!\\d)$");
}
