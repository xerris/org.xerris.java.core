package org.xerris.core.validation;

import org.junit.jupiter.api.Test;

import static org.xerris.core.validation.IntegerPredicates.*;

public class IntegerValidationTests  extends BaseValidationTests{

    @Test
    public void equals() {
        Validate.begin().is(2).equalTo(2, "2 == 2").check();
        shouldFail(Validate.begin().is(2).equalTo(1, "2 != 1"));
    }

    @Test
    public void NotEqual() {
        Validate.begin().is(2,  NotEqual, 1, "2 != 1").check();
        shouldFail(Validate.begin().is(2, NotEqual, 2, "2 != 2"));
    }

    @Test
    public void lessThan() {
        Validate.begin().is(1, LessThan, 2, "1 < 2").check();
        shouldFail(Validate.begin().is(2, LessThan, 1, "! 2 < 1"));
    }

    @Test
    public void lessThanEaual() {
        Validate.begin().is(1, LessThanEqual, 2, "1 <= 2").check();
        Validate.begin().is(2, LessThanEqual, 2, "2 <= 2").check();
        shouldFail(Validate.begin().is(3, LessThanEqual, 1, "!2 <= 1"));
    }
    @Test
    public void greaterThan() {
        Validate.begin().is(4, GreaterThan, 3, "4 > 3").check();
        shouldFail(Validate.begin().is(2, GreaterThan, 4, "!2 > 4"));
    }

    @Test
    public void greaterThanEqual() {
        Validate.begin().is(3, GreaterThanEqual, 3, "3 >= 2").check();
        Validate.begin().is(3, GreaterThanEqual, 2, "3 >= 2").check();
        shouldFail(Validate.begin().is(4, GreaterThanEqual, 5, "!4 >= 5"));
    }
}
