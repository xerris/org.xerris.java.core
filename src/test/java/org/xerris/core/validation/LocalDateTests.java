package org.xerris.core.validation;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.xerris.core.validation.DatePredicates.*;

public class LocalDateTests {
    private final LocalDate dec31 = LocalDate.parse("2019-12-31");
    private final LocalDate jan1 = LocalDate.parse("2020-01-01");
    private final LocalDate jan2 = LocalDate.parse("2020-01-02");

    @Test
    public void lessThan() {
        Validate.begin().is(jan1, LessThan, jan2, "should be earlier").check();
        shouldFail(Validate.begin().is(jan2, LessThan, jan1, "should not be earlier"));
    }

    @Test
    public void lessThanEqual() {
        Validate.begin().is(dec31, LessThanEqual, jan1, "should be ok").check();
        Validate.begin().is(dec31, LessThanEqual, dec31, "should be ok").check();
        shouldFail(Validate.begin().is(jan2, LessThan, jan1, "should not be earlier"));
    }

    @Test
    public void greaterThan() {
        Validate.begin().is(jan2, GreaterThan, jan1, "should be later").check();
        shouldFail(Validate.begin().is(jan1, GreaterThan, jan2, "should fail"));
    }

    @Test
    public void greaterThanEqual() {
        Validate.begin().is(jan1, GreaterThanEqual, dec31, "should be ok").check();
        Validate.begin().is(jan2, GreaterThanEqual, jan2, "should be ok").check();
        shouldFail(Validate.begin().is(dec31 , GreaterThanEqual, jan1, "should fail"));
    }

    private void shouldFail(Validate validate) {
        assertThatThrownBy(validate::check) .isInstanceOf(ValidationException.class);
    }
}
