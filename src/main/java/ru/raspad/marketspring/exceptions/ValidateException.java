package ru.raspad.marketspring.exceptions;

import java.util.List;
import java.util.stream.Collectors;

public class ValidateException extends RuntimeException{
    private List<String> errors;

    public ValidateException(List<String> errors) {
        super(String.join(", ", errors));
        this.errors = errors;
    }
}
