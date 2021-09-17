package org.xerris.core.range;

@FunctionalInterface
public interface Increment<T> {
    T next(T current);
}
