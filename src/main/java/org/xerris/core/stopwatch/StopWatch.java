package org.xerris.core.stopwatch;

import org.xerris.core.utils.ICommand;
import org.xerris.core.utils.IFunction;

import java.util.ArrayList;
import java.util.Collection;

public class StopWatch implements IStopWatch{

    private TimedTask parent;
    private Collection<TimedTask> internal;

    StopWatch() { this.internal = new ArrayList<>(); }

    void add(TimedTask stopWatch) {
        if (parent == null)
            this.parent = stopWatch;
        else
            this.internal.add(stopWatch);
    }

    public String name() { return (this.parent.name()); }
    public long duration() { return parent.duration(); }
    public long seconds() { return parent.seconds(); }
    public long milliseconds() { return parent.milliseconds(); }

    public <T> T start(String name, IFunction<T> action, T subject) {
        return parent.start(name, action, subject);
    }
    public void start(String name, ICommand action) {
        parent.start(name, action);
    }

    public void writeOn(StringBuilder builder, long duration) {
        builder.append(String.format("\nTiming Summary for %s\n", parent.name()));
        parent.writeOn(builder, duration);

        for(IStopWatch each : internal) {
            builder.append("\t...");
            each.writeOn(builder, parent.duration());
        }
    }
}
