package org.xerris.core.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class Clock {
    private static IClock local;
    private static IClock utc;

    public static IClock local() {
        if (local == null) local = new TimezoneClock(ZoneId.systemDefault());
        return local;
    }

    public static IClock utc() {
        if (utc == null) local = new TimezoneClock(ZoneOffset.UTC);
        return utc;
    }

    public static LocalDateTime endOfTime() { return LocalDateTime.MAX; }
}
