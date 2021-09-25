package org.xerris.core.utils;

public class TypedCommand<T> implements ITypedCommand<T>{

    private final IFunction<T> toRun;

    public TypedCommand(IFunction<T> toRun) {
        this.toRun = toRun;
    }

    @Override
    public void run(T subject) {
        if (toRun != null)
            toRun.run(subject);
    }
}
