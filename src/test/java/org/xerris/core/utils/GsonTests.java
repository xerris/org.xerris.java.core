package org.xerris.core.utils;

import org.junit.jupiter.api.Test;
import org.xerris.core.testDomain.Gender;
import org.xerris.core.testDomain.Person;

import static org.xerris.core.Assertions.isEqualTo;
import static org.xerris.core.utils.json.Json.fromJson;
import static org.xerris.core.utils.json.Json.toJson;

public class GsonTests {

    @Test
    public void serialize() {
        Person santa = new Person("Santa", 1000, Gender.Male);
        String asJson = toJson(santa);
        Person fromJson = fromJson(asJson, Person.class);

        isEqualTo(santa, fromJson);
    }
}
