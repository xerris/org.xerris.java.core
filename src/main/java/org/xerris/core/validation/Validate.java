package org.xerris.core.validation;

import org.xerris.core.validation.validators.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
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
    private Dictionary<Type, IValidator<? extends Object>> validators = new Hashtable<>();

    private Validate() {
        simpleTests = new ArrayList<>();
        compareTests = new ArrayList<>();

        validators.put(String.class, new StringValidator(this));
        validators.put(LocalDateTime.class, new LocalDateTimeValidator(this));
        validators.put(LocalDate.class, new LocalDateValidator(this));
        validators.put(Integer.class, new IntegerValidator(this));
        validators.put(Long.class, new LongValidator(this));
        validators.put(Double.class, new DoubleValidator(this));

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

    public StringValidator is(String topic) {
        return (StringValidator)getValidator(String.class, topic);
    }
    public IntegerValidator is(int topic) { return (IntegerValidator) getValidator(Integer.class, topic); }
    public LongValidator is(long topic) { return (LongValidator) getValidator(Long.class, topic); }
    public DoubleValidator is(double topic) { return (DoubleValidator) getValidator(Double.class, topic); }

    public LocalDateTimeValidator is(LocalDateTime topic) {
        return (LocalDateTimeValidator)getValidator(LocalDateTime.class, topic);
    }

    public LocalDateValidator is(LocalDate topic) {
        return (LocalDateValidator)getValidator(LocalDate.class, topic);
    }

    private <T> IValidator<T> getValidator(Class<T> target, T topic) {
        IValidator<T> validator = (IValidator<T>)validators.get(target);
        validator.setSubject(topic);
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

    public <T> Validate isNotNull(T subject, String message) {
        if (subject != null) return this;
        throw new ValidationException(message);
    }
}
