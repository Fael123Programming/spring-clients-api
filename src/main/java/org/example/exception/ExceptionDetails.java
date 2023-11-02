package org.example.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {
    protected String title, details, developerMessage;
    protected int status;
    protected LocalDateTime timestamp;
}
