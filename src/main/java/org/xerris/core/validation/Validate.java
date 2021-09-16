package org.xerris.core.validation;

import org.xerris.core.validation.validators.IValidator;
import org.xerris.core.validation.validators.LocalDateTimeValidator;
import org.xerris.core.validation.validators.StringValidator;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Validate {

    private final List<org.xerris.core.validation.Condition<Predicate, String>> simpleTests;
    private final List<ComparatorCondition<BiPredicate, Object>> compareTests;
    private final ValidationResult result;
    private final ValidationProxy proxy;
    private Dictionary<Type, IValidator<? extends Object>> validators = new Hashtable<>();

    private Validate() {
        simpleTests = new ArrayList<>();
        compareTests = new ArrayList<>();

        validators.put(String.class, new StringValidator(this));
        validators.put(LocalDateTime.class, new LocalDateTimeValidator(this));

        proxy = new ValidationProxy(this);
        result = new ValidationResult();
    }

    public static Validate begin() {
        return new Validate();
    }

    public <T> Validate is(Predicate<T> predicate, T subject, String message) {
        simpleTests.add(Condition.of(predicate, subject, message));
        return this;
    }

    public <T> Validate is(T first, BiPredicate predicate, T second, String message) {
        compareTests.add(ComparatorCondition.of(predicate, first, second, message));
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
        for(ComparatorCondition<BiPredicate, Object> each : compareTests) {
            if (!each.first().test(each.second(), each.comparitor()))
                result.add(each.message());
        }
    }

    public ValidationProxy is() { return proxy; }

    public StringValidator is(String topic) {
        StringValidator validator = (StringValidator)validators.get(String.class);
        validator.setSubject(topic);
        return validator;
    }

    public LocalDateTimeValidator is(LocalDateTime value) {
        LocalDateTimeValidator validator = (LocalDateTimeValidator)validators.get(LocalDateTime.class);
        validator.setSubject(value);
        return validator;
    }
    
    public <T> Validate add(Predicate<T> predicate, T value, String message) {
        simpleTests.add(Condition.of(predicate, value, message));
        return this;
    }

    public <T> Validate add(BiPredicate<T, T> greaterThan, T subject, T comparison, String message) {
        compareTests.add(ComparatorCondition.of(greaterThan, subject, comparison, message));
        return this;
    }
}
