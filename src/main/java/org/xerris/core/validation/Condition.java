package org.xerris.core.validation;

import org.xerris.core.utils.Pair;

public class Condition<Predicate, T> extends Pair<Predicate, T>
{
    private final String message;

    protected Condition(Predicate predicate, T first, String message) {
        super(predicate, first);
        this.message = message;
    }

    public String message() { return this.message; }

    public static <T> Condition<java.util.function.Predicate, String> of(java.util.function.Predicate predicate, T first, String message) {
        return new Condition(predicate, first, message);
    }
}

