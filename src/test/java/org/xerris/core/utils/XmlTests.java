package org.xerris.core.utils;

import org.junit.jupiter.api.Test;
import org.xerris.core.Assertions;
import org.xerris.core.IAssertion;
import org.xerris.core.testDomain.Event;
import org.xerris.core.testDomain.Gender;
import org.xerris.core.testDomain.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.xerris.core.utils.xml.Xml.fromXml;
import static org.xerris.core.utils.xml.Xml.toXml;

public class XmlTests {

    @Test
    public void serialize() {
        Person santa = new Person("Santa", 1000, Gender.Male, LocalDate.now());
        runTest(santa, Person.class, Assertions::isEqualTo);
    }

    @Test
    public void canSerializeLocalDateTime() {
        Event christmas =
                new Event("Christmas", LocalDateTime.of(2021, 12, 25, 0, 0, 0));

        runTest(christmas, Event.class, Assertions::isEqualTo);
    }

    private <T> void runTest(T subject, Class<T> ofType, IAssertion<T> assertion) {
        String asJson = toXml(subject);
        T fromJson = fromXml(asJson, ofType);
        assertion.test(subject, fromJson);
    }
}