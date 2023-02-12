package org.xerris.core.stopwatch;

import org.xerris.core.utils.ICommand;
import org.xerris.core.utils.IFunction;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimedTask implements IStopWatch {

    private String name;
    private LocalDateTime start;
    private long duration;

    public <T> T start(String name, IFunction<T> action, T subject) {
        this.start(name);
        T response = action.run(subject);
        stop();
        return response;
    }

    @Override
    public void start(String name, ICommand action) {
        this.start(name);
        action.run();
        stop();
    }

    private void start(String name) {
        this.name = name;
        start = LocalDateTime.now();
    }

    private void stop() {
        duration = ChronoUnit.MILLIS.between(start, LocalDateTime.now());
    }

    public String name() { return this.name; }
    public long duration() { return duration; }
    public long seconds() { return duration/1000;}
    public long milliseconds() { return duration%1000;}

    public void writeOn(StringBuilder builder, long totalDuration) {
        builder.append(String.format("%20s - %d:%03d\n", name(), seconds(), milliseconds()));
    }
}
