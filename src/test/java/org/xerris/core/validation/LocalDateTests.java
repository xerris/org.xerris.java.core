package org.xerris.core.validation;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class LocalDateTests extends BaseValidationTests {
    private final LocalDateTime dec31 = LocalDateTime.of(2019, 12, 31, 0, 0, 0);
    private final LocalDateTime jan1 = LocalDateTime.of(2020, 1, 1, 0,0,0);
    private final LocalDateTime jan2 = LocalDateTime.of(2020,1,2,0,0,0);

    @Test
    public void  notNull() {
        Validate.begin().is(dec31).notNull("dec31 is not null").check();
        shouldFail(Validate.begin().is((LocalDateTime) null).notNull("is null"));
    }

    @Test
    public void equals() {
        Validate.begin().is(jan1).equalTo(jan1, "should equal").check();
        shouldFail(Validate.begin().is(jan2).equalTo(jan1, "should not equal"));
    }

    @Test
    public void notEqual() {
        Validate.begin().is(jan1).notEqualTo(jan2, "should not equal").check();
        shouldFail(Validate.begin().is(jan2).notEqualTo(jan2, "should  equal"));
    }

    @Test
    public void lessThan() {
        Validate.begin().is(jan1).lessThan(jan2, "should be earlier").check();
        shouldFail(Validate.begin().is(jan2).lessThan(jan1, "should not be earlier"));
    }

    @Test
    public void lessThanEqual() {
        Validate.begin().is(dec31).lessThanOrEqualTo(jan1, "should be ok").check();
        Validate.begin().is(dec31).lessThanOrEqualTo(dec31, "should be ok").check();
        shouldFail(Validate.begin().is(jan2).lessThanOrEqualTo(jan1, "should not be earlier"));
    }

    @Test
    public void greaterThan() {
        Validate.begin().is(jan2).greaterThan(jan1, "should be later").check();
        shouldFail(Validate.begin().is(jan1).greaterThan(jan2, "should fail"));
    }

    @Test
    public void greaterThanEqual() {
        Validate.begin().is(jan1).greaterThanOrEqualTo(dec31, "should be ok").check();
        Validate.begin().is(jan2).greaterThanOrEqualTo(jan2, "should be ok").check();
        shouldFail(Validate.begin().is(dec31).greaterThanOrEqualTo(jan1, "should fail"));
    }
}
