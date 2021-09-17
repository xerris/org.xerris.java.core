package org.xerris.core.range;

import org.xerris.core.exceptions.ArgumentException;
import org.xerris.core.utils.ICommand;
import org.xerris.core.utils.ITypedCommand;

public class Range<T extends Comparable<T>> {
    private final T start;
    private final T end;
    private Increment<T> inc;

    public Range(T start, T end) {
        if (start.compareTo(end) > 0)
            throw new ArgumentException("start must not be > end");
        this.start = start;
        this.end = end;
    }

    public Range(T start, T end, Increment<T> increment) {
        this.start = start;
        this.end = end;
        this.inc = increment;
    }

    public T getStart() { return start; }
    public T getEnd() { return end; }

    public boolean includes(T subject) {
        return subject.compareTo(start) >= 0 && subject.compareTo(end) <= 0;
    }

    public void forEach(Increment<T> incrementor, ICommand task) {
        if (incrementor == null) throw new ArgumentException("increment not set");
        for (T x = start; x.compareTo(end) <= 0; x = incrementor.next(x)) {
            task.run();
        }
    }

    public void forEach(ICommand task) {
        forEach(this.inc, task);
    }

    public <U> void forEach(Increment<T> incrementor, U subject, ITypedCommand<U> action) {
        if (incrementor == null) throw new ArgumentException("increment not set");
        for (T x = start; x.compareTo(end) <= 0; x = incrementor.next(x)) {
            action.run(subject);
        }
    }

    public <U> void forEach(U subject, ITypedCommand<U> action) {
        forEach(this.inc, subject, action);
    }
}
