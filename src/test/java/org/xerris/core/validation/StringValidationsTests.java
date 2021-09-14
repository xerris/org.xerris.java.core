package org.xerris.core.validation;

import org.junit.jupiter.api.Test;

import static org.xerris.core.validation.StringPredicates.*;

public class StringValidationsTests extends BaseValidationTests {

    @Test
    public void Equals() {
        Validate.begin().is("one", Equals, "one", "one  == one").check();
        Validate.begin().is(null, Equals, null, "one  == one").check();

        shouldFail(Validate.begin().is(null, Equals, "", "not equal"));
        shouldFail(Validate.begin().is("one", Equals, "two", "one != two"));
    }

    @Test
    public void NotEqual() {
        Validate.begin().is("one", NotEqual, "two", "one  == one").check();
        Validate.begin().is(null, NotEqual, "", "one  == one").check();

        shouldFail(Validate.begin().is(null, NotEqual, null, "not equal"));
        shouldFail(Validate.begin().is("one", NotEqual, "one", "one != two"));
    }

    @Test
    public void NotNull() {
        Validate.begin().is(NotNull, "hi", "not null").check();
        shouldFail(Validate.begin().is(NotNull, null, "is null"));
    }

    @Test
    public void isNotEmpty() {
        Validate.begin().is(NotEmpty, "hi", "should not be empty").check();
        shouldFail(Validate.begin().is(NotEmpty, "", "should not be empty"));
    }

    @Test
    public void isEmail() {
        Validate.begin().is(Email, "hi@hi.com", "hi").check();
        shouldFail(Validate.begin().is(Email, "hi, I'm not an email", "hi"));
    }

    @Test
    public void isPhoneNumber() {
        Validate.begin().is(PhoneNumber, "888 472-2222", "phone me").check();
        shouldFail(Validate.begin().is(PhoneNumber, "8888-888-8888", "bad number"));
    }

    @Test
    public void isNotEmpty_MultipleHappyTests() {
        Validate.begin()
                .is(NotEmpty, "one", "one")
                .is(Email, "info@xerris.com", "two")
                .is(Integer, "123", "three")
                .is(Numeric, "123.0", "three")
                .is(PhoneNumber, "888 472-2222", "call me")
                .check();
    }

    @Test
    public void isNotEmpty_MultipleHasOneBad() {
        shouldFail(Validate.begin()
                           .is(NotEmpty, "one", "one")
                           .is(NotEmpty, null, "two")
                           .is(NotEmpty, "three", "three")
        );
    }
}
