package org.xerris.core.validation;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public abstract class BaseValidationTests {
    protected void shouldFail(Validate validate) {
        assertThatThrownBy(validate::check) .isInstanceOf(ValidationException.class);
    }
}
