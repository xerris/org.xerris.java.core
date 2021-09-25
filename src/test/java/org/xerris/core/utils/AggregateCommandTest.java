package org.xerris.core.utils;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AggregateCommandTest {
    private int count = 0;

    @Test
    public void one() {
        new Command(() -> this.count = 10)
            .run();
        assertThat(count).isEqualTo(10);
    }

    @Test
    public void two() {
        new Command(() -> this.count = 1)
                .then(new Command(() -> this.count = 2))
                .run();
        assertThat(count).isEqualTo(2);
    }
}
