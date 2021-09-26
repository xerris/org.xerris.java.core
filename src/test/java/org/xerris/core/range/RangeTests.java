package org.xerris.core.range;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.xerris.core.testDomain.Gender;
import org.xerris.core.testDomain.Person;
import org.xerris.core.exceptions.ArgumentException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RangeTests {

    private int count;

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

    @Test
    public void forEach_incrementorOnConstructor() {
        Range<Integer> range = new Range<>(1, 5, (x) -> x + 1);
        count =0;
        range.forEach(() -> count +=1 );
        assertThat(count).isEqualTo(5);
    }

    @Test
    public void forEach_incrementorOnForEach() {
        Range<Integer> range = new Range<>(1, 5);
        count =0;
        range.forEach((x) -> x + 1, () -> count +=1);
        assertThat(count).isEqualTo(5);
    }

    @Test
    public void forEach_withTypedParameterAndIncrementorInConstructor() {
        Person littleJoey = new Person("Joey", 1, Gender.Other);
        assertThat(littleJoey.getAge()).isEqualTo(1);

        new Range<>(1, 5, x -> x+=1).forEach(littleJoey, Person::birthday);

        assertThat(littleJoey.getAge()).isEqualTo(6);
    }

    @Test
    public void forEach_withTypedParameterAndIncrementorMethodArgument() {
        Person littleJoey = new Person("Joey", 0, Gender.Male);
        assertThat(littleJoey.getAge()).isEqualTo(0);

        new Range<>(1, 5).forEach(x -> x+=1, littleJoey, Person::birthday);

        assertThat(littleJoey.getAge()).isEqualTo(5);
    }

}
