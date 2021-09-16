package org.xerris.core.time;

import org.junit.jupiter.api.Test;
import org.xerris.core.validation.Validate;
import java.time.LocalDateTime;

public class ClockTest {

    @Test
    public void endOfTime() {
        LocalDateTime endOfTime = Clock.endOfTime();

        Validate.begin()
                .is(endOfTime).Equal(LocalDateTime.of(9999, 12, 31, 23, 59, 59), "should match")
                .check();
    }

}
