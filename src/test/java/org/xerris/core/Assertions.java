package org.xerris.core;

import org.xerris.core.testDomain.Person;
import org.xerris.core.validation.Validate;

public class Assertions {
    public static void isEqualTo(Person actual, Person expected) {
        Validate.begin()
                .isNotNull(actual, "actual should not be null")
                .isNotNull(expected, "expected should not be null")
                .is(actual.getName()).equalTo(expected.getName(), "name)")
                .is(actual.getAge()).equalTo(expected.getAge(), "age")
                .is(actual.getBirthDate()).equalTo(expected.getBirthDate(), "birthDate")
                .is(actual.getGender(), Object::equals, expected.getGender(), "gender")
                .check();
    }
}
