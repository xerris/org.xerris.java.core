package org.xerris.core.validation;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.xerris.core.validation.DatePredicates.*;

public class LocalDateTests extends BaseValidationTests {
    private final LocalDate dec31 = LocalDate.parse("2019-12-31");
    private final LocalDate jan1 = LocalDate.parse("2020-01-01");
    private final LocalDate jan2 = LocalDate.parse("2020-01-02");

    @Test
    public void  notNull() {
        Validate.begin().is(NotNull, dec31, "dec31 is not null").check();
        shouldFail(Validate.begin().is(NotNull, null, "is null"));
    }

    @Test
    public void equals() {
        Validate.begin().is(jan1, Equals, jan1, "should equal").check();
        shouldFail(Validate.begin().is(jan2, Equals, jan1, "should not equal"));
    }

    @Test
    public void notEqual() {
        Validate.begin().is(jan1, NotEqual, jan2, "should not equal").check();
        shouldFail(Validate.begin().is(jan2, NotEqual, jan2, "should  equal"));
    }

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
}
