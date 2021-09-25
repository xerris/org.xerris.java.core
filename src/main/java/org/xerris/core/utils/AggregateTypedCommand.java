package org.xerris.core.utils;

import java.util.Arrays;
import java.util.Collection;

public class AggregateTypedCommand<T> implements ITypedCommand<T> {

    private Collection<ITypedCommand<T>> commands;

    public AggregateTypedCommand(ITypedCommand<T> first, ITypedCommand<T> second) {
        commands = Arrays.asList(first, second);
    }

    @Override
    public void run(T subject) {
        for(ITypedCommand<T> each : commands)
            each.run(subject);
    }
}
