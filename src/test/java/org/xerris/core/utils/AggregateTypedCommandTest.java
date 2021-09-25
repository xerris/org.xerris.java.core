package org.xerris.core.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xerris.core.Person;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AggregateTypedCommandTest {
    private Person monty;

    @BeforeEach
    public void beforeEach() { monty = new Person("Monty", 13);}

    @Test
    public void one() {
        assertThat(monty.getAge()).isEqualTo(13);

        new TypedCommand<>(Person::happyBirthday).run(monty);
        assertThat(monty.getAge()).isEqualTo(14);
    }

    @Test
    public void then() {
        assertThat(monty.getAge()).isEqualTo(13);

        new TypedCommand<>(Person::happyBirthday)
                .then(new TypedCommand<>(Person::happyBirthday))
                .run(monty);
        assertThat(monty.getAge()).isEqualTo(15);
    }
}
