package org.xerris.core;

import org.xerris.core.exceptions.ArgumentException;

public class Range<T extends Comparable<T>> {
    private final T start;
    private final T end;

    public Range(T start, T end) {
        if (start.compareTo(end) > 0)
            throw new ArgumentException("start must not be > end");
        this.start = start;
        this.end = end;
    }

    public T getStart() { return start; }
    public T getEnd() { return end; }

    public boolean includes(T subject) {
        return subject.compareTo(start) >= 0 && subject.compareTo(end) <= 0;
    }
}
