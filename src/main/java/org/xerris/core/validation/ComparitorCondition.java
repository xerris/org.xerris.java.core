package org.xerris.core.validation;

import org.xerris.core.utils.Pair;

public class ComparitorCondition<BiPredicate, T extends Object> extends Pair<BiPredicate, T> {
    private final T comparitor;
    private final String message;

    protected ComparitorCondition(BiPredicate predicate, T first, T second, String message) {
        super(predicate, first);
        this.comparitor = second;
        this.message = message;
    }

    public T comparitor() { return this.comparitor; }
    public String message() { return this.message; }

    public static <T> ComparitorCondition<java.util.function.BiPredicate, T> of(java.util.function.BiPredicate predicate, T first, T second, String message) {
        return new ComparitorCondition(predicate, first, second, message);
    }
}