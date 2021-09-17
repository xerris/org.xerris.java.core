package org.xerris.core.validation.validators;

import org.xerris.core.validation.Validate;

import java.util.Objects;
import java.util.function.Predicate;

public class StringValidator extends BaseValidator<String> {

    public StringValidator(Validate validate) {
        super(validate);
    }

    public Validate notEmpty(String message) { return add(notEmpty, message); }
    public Validate notBlank(String message) { return add(notBlank, message); }
    public Validate anEmail(String message) { return add(email, message);  }
    public Validate aPhoneNumber(String message) { return add(PhoneNumber, message); }
    public Validate anInteger(String message) { return add(Integer, message); }
    public Validate numeric(String message) { return add(Numeric, message); }

    private final static Predicate<Object> notNull = Objects::nonNull;
    private final static Predicate<String> notBlank = (String s) -> notNull.test(s) && !s.isBlank();
    private final static Predicate<String> notEmpty = (String s) -> notNull.test(s) && !s.trim().isEmpty();
    private final static Predicate<String> email = (String s) -> notNull.test(s) && s.matches("^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$");
    private final static Predicate<String> Numeric = (String s) -> notNull.test(s) && s.matches("^([-+]?[0-9]\\d*(\\.\\d+)?|[0]*\\.\\d\\d*)$");
    private final static Predicate<String> Integer = (String s) -> notNull.test(s) && s.matches("^([-+]?[0-9]\\d*)$");
    private final static Predicate<String> PhoneNumber = (String s) -> notNull.test(s) && s.matches("^(\\(?)[2-9](\\d{2})[- ]?([).\\s]?)[2-9](\\d{2})[- ]?([-.\\s]?)(\\d{4})(?!\\d)$");

}
