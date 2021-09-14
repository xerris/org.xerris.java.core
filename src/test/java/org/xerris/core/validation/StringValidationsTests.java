package org.xerris.core.validation;

import org.junit.jupiter.api.Test;

import static org.xerris.core.validation.StringPredicates.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class StringValidationsTests {

    @Test
    public void isNotEmpty() {
        Validate.begin().is(NotEmpty, "hi", "should not be empty").check();
    }

    @Test
    public void isNotEmpty_empty() {
        shouldFail(Validate.begin().is(NotEmpty, "", "should be empty"));
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

    private void shouldFail(Validate validate) {
        assertThatThrownBy(validate::check) .isInstanceOf(ValidationException.class);
    }
}
