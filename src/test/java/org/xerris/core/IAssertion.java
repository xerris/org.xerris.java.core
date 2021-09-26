package org.xerris.core;

public interface IAssertion<T> {
    void test(T actual, T expected);
}
