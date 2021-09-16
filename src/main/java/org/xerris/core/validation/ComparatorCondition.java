package org.xerris.core.validation;

import org.xerris.core.utils.Pair;

public class ComparatorCondition<BiPredicate, T extends Object> extends Pair<BiPredicate, T> {
    private final T comparator;
    private final String message;

    protected ComparatorCondition(BiPredicate predicate, T first, T second, String message) {
        super(predicate, first);
        this.comparator = second;
        this.message = message;
    }

    public T comparitor() { return this.comparator; }
    public String message() { return this.message; }

    public static <T> ComparatorCondition<java.util.function.BiPredicate, T> of(java.util.function.BiPredicate predicate, T first, T second, String message) {
        return new ComparatorCondition(predicate, first, second, message);
    }
}