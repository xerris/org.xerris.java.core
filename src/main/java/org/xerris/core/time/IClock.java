package org.xerris.core.time;

import java.time.LocalDateTime;

public interface IClock
{
    LocalDateTime now();
    LocalDateTime nowUtc();
    LocalDateTime today();
    void freeze();
    void Freeze(LocalDateTime timeToFreeze);
    void thaw();
}