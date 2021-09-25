package org.xerris.core.utils;

public interface ITypedCommand<T> {
    void run(T subject);

    default ITypedCommand<T> then(ITypedCommand<T> next) {
        return new AggregateTypedCommand<T>(this, next);
    }
}
