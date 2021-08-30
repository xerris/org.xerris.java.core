package org.xerris.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.xerris.core.exceptions.ArgumentException;

import static com.google.common.truth.Truth.assertThat;

public class RangeTests {

    @Test
    public void create() {
        Range<Integer> range = new Range<>(1, 2);

        assertThat(range).isNotNull();
        assertThat(range.getStart()).isEqualTo(1);
        assertThat(range.getEnd()).isEqualTo(2);
    }

    @Test
    public void createStartBiggerThanEnd() {
        Assertions.assertThrows(ArgumentException.class, () -> new Range<>(2, 1));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 1.1, 1.2, 1.3})
    public void includes(double value) {
        Range<Double> range = new Range<>(1.0, 1.3);
        assertThat(range.includes(value)).isTrue();
    }

    @Test
    public void tooLittle() {
        Range<Float> range = new Range<>(1.0f, 1.2f);
        assertThat(range.includes(0.9999f)).isFalse();
    }

    @Test
    public void tooMuch() {
        Range<Float> range = new Range<>(1.0f, 1.2f);
        assertThat(range.includes(1.200001f)).isFalse();
    }
}
