package org.xerris.core.stopwatch;

import org.xerris.core.utils.ICommand;
import org.xerris.core.utils.IFunction;

public interface IStopWatch {
    <T> T start(String name, IFunction<T> action, T subject);
    void start(String name, ICommand action);
    String name();
    long duration();
    long seconds();
    long milliseconds();
    void writeOn(StringBuilder builder, long duration);
}
