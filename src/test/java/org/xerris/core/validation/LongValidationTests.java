package org.xerris.core.validation;

import org.junit.jupiter.api.Test;

import static org.xerris.core.validation.LongPredicates.*;

public class LongValidationTests extends BaseValidationTests {

        @Test
        public void equals() {
            Validate.begin().is(2L, Equals, 2L, "2 == 2").check();
            shouldFail(Validate.begin().is(2L, Equals, 1L, "2 != 1"));
        }
        @Test
        public void NotEqual() {
            Validate.begin().is(2L,  NotEqual, 1L, "2 != 1").check();
            shouldFail(Validate.begin().is(2L, NotEqual, 2L, "2 != 2"));
        }

        @Test
        public void lessThan() {
            Validate.begin().is(1L, LessThan, 2L, "1 < 2").check();
            shouldFail(Validate.begin().is(2L, LessThan, 1L, "! 2 < 1"));
        }

        @Test
        public void lessThanEaual() {
            Validate.begin().is(1L, LessThanEqual, 2L, "1 <= 2").check();
            Validate.begin().is(2L, LessThanEqual, 2L, "2 <= 2").check();
            shouldFail(Validate.begin().is(3L, LessThanEqual, 1L, "!2 <= 1"));
        }
        @Test
        public void greaterThan() {
            Validate.begin().is(4L, GreaterThan, 3L, "4 > 3").check();
            shouldFail(Validate.begin().is(2L, GreaterThan, 4L, "!2 > 4"));
        }

        @Test
        public void greaterThanEqual() {
            Validate.begin().is(3L, GreaterThanEqual, 3L, "3 >= 2").check();
            Validate.begin().is(3L, GreaterThanEqual, 2L, "3 >= 2").check();
            shouldFail(Validate.begin().is(4L, GreaterThanEqual, 5L, "!4 >= 5"));
        }
    }

