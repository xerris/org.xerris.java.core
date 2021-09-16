package org.xerris.core.utils;

@FunctionalInterface
public interface IFunction<T> {
    T run(T subject);
}
