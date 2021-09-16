package org.xerris.core.utils;

public interface ITypedCommand<T> {
    void run(T subject);
}
