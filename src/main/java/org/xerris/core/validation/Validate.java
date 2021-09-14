package org.xerris.core.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Validate {

    private final List<Condition<Predicate, String>> simpleTests;
    private final List<ComparitorCondition<BiPredicate, Object>> compareTests;
    private final ValidationResult result;

    private Validate() {
        simpleTests = new ArrayList<>();
        compareTests = new ArrayList<>();
        result = new ValidationResult();
    }

    public static Validate begin() {
        return new Validate();
    }

    public <T> Validate is(Predicate<T> predicate, T subject, String message) {
        simpleTests.add(Condition.of(predicate, subject, message));
        return this;
    }

    public <T> Validate is(T first, BiPredicate<T,T> predicate, T second, String message) {
        compareTests.add(ComparitorCondition.of(predicate, first, second, message));
        return this;
    }

    public void check() {
        runSimpleTests();
        runCompareTests();
        result.check();
    }

    private void runSimpleTests() {
        for(Condition<Predicate, String> each : simpleTests) {
            if (!each.first().test(each.second()))
                result.add(each.message());
        }
    }

    private void runCompareTests() {
        for(ComparitorCondition<BiPredicate, Object> each : compareTests) {
            if (!each.first().test(each.second(), each.comparitor()))
                result.add(each.message());
        }
    }
}
