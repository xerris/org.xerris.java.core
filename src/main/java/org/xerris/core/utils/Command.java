package org.xerris.core.utils;

public class Command implements ICommand {

    private final ICommand toRun;

    public Command() { toRun = null; }
    public Command(ICommand toRun) { this.toRun = toRun; }

    @Override
    public void run() {
        if (toRun != null)
            toRun.run();
    }
}
