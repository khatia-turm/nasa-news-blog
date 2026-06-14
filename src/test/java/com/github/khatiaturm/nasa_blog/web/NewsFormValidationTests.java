package com.github.khatiaturm.nasa_blog.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;


//checks if invalid user inputs are rejected correctly
class NewsFormValidationTests {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void rejectsBlankAndTooShortValuesWithMessages() {
        NewsForm form = new NewsForm();
        form.setTitle("");
        form.setContent("short");

        Set<ConstraintViolation<NewsForm>> violations = validator.validate(form);

        assertThat(violations)
                .extracting(ConstraintViolation::getMessage)
                .contains(
                        "Title is required.",
                        "Title must be between 3 and 120 characters.",
                        "Content must be between 10 and 5000 characters.");
    }
}
