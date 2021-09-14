package org.xerris.core.validation;

import java.util.ArrayList;
import java.util.Collection;

public class ValidationResult {
    private final Collection<String> errors;

    public ValidationResult() { errors = new ArrayList<>(); }

    public ValidationResult(String message) {
        errors = new ArrayList<>();
        errors.add(message);
    }

    public boolean isValid() { return errors.isEmpty(); }

    public void throwIfInvalid() {
        if(!isValid()) throw new IllegalArgumentException(getMessage());
    }

    public void throwIfInvalid(String fieldName) {
        if(!isValid()) throw new IllegalArgumentException(fieldName + " : " + getMessage());
    }

    public String getMessage() {
        StringBuffer buffer = new StringBuffer();
        for (String each : errors)
            buffer.append(String.format("%s\n", each));
        return buffer.toString();
    }

    public void add(String message) {
        errors.add(message);
    }

    public void check() {
        if (isValid()) return;
        throw new ValidationException(getMessage());
    }

    public static ValidationResult ok() { return new ValidationResult(); }
    public static ValidationResult fail(String message) { return new ValidationResult(message); }
}
