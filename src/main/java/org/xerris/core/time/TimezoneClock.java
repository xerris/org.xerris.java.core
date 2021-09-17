package org.xerris.core.time;

import java.time.*;

public class TimezoneClock implements IClock {

    private static final ZoneId mountainId = ZoneId.of("America/Edmonton");
    private static final ZoneId easternId = ZoneId.of("America/New_York");

    private ZoneId timeZone;

    public TimezoneClock(ZoneId timeZoneId) {
        this.timeZone = timeZoneId;
    }

    @Override
    public LocalDateTime now() {
        return ClockManager.now().atZone(timeZone).toLocalDateTime();
    }

    @Override
    public LocalDateTime nowUtc() {
        return ClockManager.now().atZone(ZoneOffset.UTC).toLocalDateTime();
    }

    @Override
    public LocalDate today() { return LocalDate.now(timeZone); }

    @Override
    public void freeze() {
        ClockManager.freeze();
    }

    @Override
    public void Freeze(LocalDateTime timeToFreeze) {
        ClockManager.freeze(timeToFreeze);
    }

    @Override
    public void thaw() {
        ClockManager.thaw();
    }

    public static TimezoneClock mountain() { return new TimezoneClock(mountainId);}
    public static TimezoneClock eastern() { return new TimezoneClock(easternId);}
}
