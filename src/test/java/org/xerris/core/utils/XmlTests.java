package org.xerris.core.utils;

import org.junit.jupiter.api.Test;
import org.xerris.core.testDomain.Gender;
import org.xerris.core.testDomain.Person;

import java.time.LocalDate;

import static org.xerris.core.Assertions.isEqualTo;
import static org.xerris.core.utils.xml.Xml.fromXml;
import static org.xerris.core.utils.xml.Xml.toXml;

public class XmlTests {

    @Test
    public void serialize() {
        Person santa = new Person("Santa", 1000, Gender.Male, LocalDate.now());
        String asJson = toXml(santa);
        Person fromJson = fromXml(asJson, Person.class);

        isEqualTo(santa, fromJson);
    }
}
