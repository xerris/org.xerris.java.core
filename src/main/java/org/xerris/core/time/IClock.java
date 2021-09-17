package org.xerris.core.time;

import java.time.LocalDateTime;
import java.time.LocalDate;

public interface IClock
{
    LocalDateTime now();
    LocalDateTime nowUtc();
    LocalDate  today();
    void freeze();
    void Freeze(LocalDateTime timeToFreeze);
    void thaw();
}