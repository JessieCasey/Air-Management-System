package com.air.managment.system.exception;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

/**
 * The ExceptionMessage class is required if we want to create message response for our exceptions.
 */
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExceptionMessage {
    HttpStatus status;
    Date date;
    String description;
    String url;

    List<SimplifiedError> errors;

    @Getter
    static class SimplifiedError {
        private final String field;
        private final String message;

        public SimplifiedError(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }
}

