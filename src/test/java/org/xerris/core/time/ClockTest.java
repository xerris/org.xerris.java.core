package org.xerris.core.time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.xerris.core.validation.Validate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClockTest {

    @AfterEach
    public void afterEach() { Clock.local().thaw(); }

    @Test
    public void endOfTime() {
        LocalDateTime endOfTime = Clock.endOfTime();
        Validate.begin()
                .is(endOfTime).equalTo(
                        LocalDateTime.of(9999, 12, 31, 23, 59, 59),
                        "should match")
                .check();
    }

    @Test
    public void today() {
        LocalDate today = Clock.local().today();
        LocalDate expected = LocalDate.now();
        Validate.begin()
                .is(today).equalTo(expected, "now")
                .check();
    }

    @Test
    public void freeze() throws InterruptedException {
        Clock.local().freeze();
        LocalDateTime frozen = Clock.local().now();
        Thread.sleep(100);
        Validate.begin()
                .is(frozen).equalTo(Clock.local().now(), "frozen")
                .check();
    }

    @Test
    public void thaw() throws InterruptedException {
        Clock.local().freeze();
        LocalDateTime frozen = Clock.local().now();
        Thread.sleep(100);

        Clock.local().thaw();
        Validate.begin()
                .is(frozen).notEqualTo(Clock.local().now(), "frozen")
                .check();
    }

}
