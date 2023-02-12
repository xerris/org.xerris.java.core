package org.xerris.core.utils;

@FunctionalInterface
public interface ICommand {
    void run();
    default ICommand then(ICommand next) {
        return new AggregateCommand(this, next);
    }
}


