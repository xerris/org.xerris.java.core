package org.xerris.core.time;

import java.time.LocalDateTime;

class ClockManager {
    private static LocalDateTime frozenTime;

    public static LocalDateTime now() {
        if (frozenTime != null)
            return frozenTime;
        return LocalDateTime.now();
    }

    public static void freeze() {
        frozenTime = LocalDateTime.now();
    }

    public static void freeze(LocalDateTime timeToFreeze) {
        frozenTime = timeToFreeze;
    }

    public static void thaw() { frozenTime = null;}
}
