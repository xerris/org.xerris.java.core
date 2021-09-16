package org.xerris.core.utils;

import java.util.Arrays;
import java.util.Collection;

public class AggregateCommand implements ICommand {

    private Collection<ICommand> commands;

    public AggregateCommand(ICommand command, ICommand next) {
        commands = Arrays.asList(command, next);
    }

    @Override
    public void run() {
        for(ICommand each : commands)
            each.run();
    }

}
