package org.xerris.core.utils;

import org.junit.jupiter.api.Test;
import org.xerris.core.Assertions;
import org.xerris.core.IAssertion;
import org.xerris.core.testDomain.Event;
import org.xerris.core.testDomain.Gender;
import org.xerris.core.testDomain.Person;

import java.time.LocalDateTime;

import static org.xerris.core.utils.json.Json.fromJson;
import static org.xerris.core.utils.json.Json.toJson;

public class GsonTests {

    @Test
    public void serialize() {
        Person santa = new Person("Santa", 1000, Gender.Male);
        runTest(santa, Person.class, Assertions::isEqualTo);
    }

    @Test
    public void canSerializeLocalDateTime() {
        Event christmas =
                new Event("Christmas", LocalDateTime.of(2021, 12, 25, 0, 0, 0));

        runTest(christmas, Event.class, Assertions::isEqualTo);
    }

    private <T> void runTest(T subject, Class<T> ofType, IAssertion<T> assertion) {
        String asJson = toJson(subject);
        T fromJson = fromJson(asJson, ofType);
        assertion.test(subject, fromJson);
    }
}
