package org.xerris.core.stopwatch;

import org.xerris.core.utils.ICommand;
import org.xerris.core.utils.IFunction;

public class CoachWithStopwatch {
    private static IStopWatch stopWatch;

    public static IStopWatch start() {
        if (stopWatch == null)
            stopWatch = new StopWatch();
        return stopWatch;
    }

    public static <T> T time(String name, IFunction<T> action, T subject) {
        return addNewTask().start(name, action, subject);
    }

    public static void time(String name, ICommand action) {
        addNewTask().start(name, action);
    }

    public static String stop() {
        if (stopWatch == null)
            throw new RuntimeException("didn't start the timer...");

        StringBuilder builder = new StringBuilder();
        stopWatch.writeOn(builder, stopWatch.duration());
        stopWatch = null;

        return builder.toString();
    }

    private static TimedTask addNewTask( ) {
        TimedTask task = new TimedTask();
        ((StopWatch)start()).add(task);
        return task;
    }
}
