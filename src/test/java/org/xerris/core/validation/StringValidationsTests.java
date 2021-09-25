package org.xerris.core.validation;

import org.junit.jupiter.api.Test;
import org.xerris.core.time.Clock;

public class StringValidationsTests extends BaseValidationTests {

    @Test
    public void Equals() {
        Validate.begin().is("one").equalTo("one", "one  == one").check();
        Validate.begin().is((String)null).equalTo(null, "one  == one").check();

        shouldFail(Validate.begin().is((String)null).equalTo("", "not equal"));
        shouldFail(Validate.begin().is("one").equalTo("two", "one != two"));
    }

    @Test
    public void NotEqual() {
        Validate.begin().is("hi").notEqualTo("bye", "passed").check();
        Validate.begin().is("").notEqualTo(null, "one  == one").check();
        Validate.begin().is((String)null).notEqualTo("bye", "one  == one").check();

        shouldFail(Validate.begin().is(".").notEqualTo(".", "not equal"));
        shouldFail(Validate.begin().is("one").notEqualTo("one", "one != two"));
    }

    @Test
    public void NotNull() {
        Validate.begin().is("hi").notNull("not null").check();
        shouldFail(Validate.begin().is((String)null).notNull("is null"));
    }

    @Test
    public void isNotEmpty() {
        Validate.begin().is("hi").notEmpty("should not be empty").check();
        shouldFail(Validate.begin().is("").notEmpty("should not be empty"));
    }

    @Test
    public void isEmail() {
        Validate.begin().is("hi@hi.com").anEmail("yes").check();
        shouldFail(Validate.begin().is("hi").anEmail("I'm not an email"));
    }

    @Test
    public void isPhoneNumber() {
        Validate.begin().is("888 472-2222").aPhoneNumber("phone me").check();
        shouldFail(Validate.begin().is("8888-888-8888").aPhoneNumber("bad number"));
        shouldFail(Validate.begin().is("188-888-8888").aPhoneNumber("bad number"));
    }

    @Test
    public void isNotEmpty_MultipleHappyTests() {
        Validate.begin()
                .is("one").notEmpty("empty")
                .is("info@xerris.com").anEmail("should be")
                .is("123").anInteger("this is a number")
                .is("123.0").numeric("this is a decimal")
                .is("888 472-2222").aPhoneNumber("call me")
                .is(Clock.local().now()).notNull("shouldn't be null")
                .check();
    }

    @Test
    public void isNotEmpty_MultipleHasOneBad() {
        shouldFail(Validate.begin()
                           .is( "one").notEmpty("one")
                           .is((String)null).notEmpty("two")
                           .is( "three").notEmpty("three")
        );
    }
}
