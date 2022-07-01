package br.com.bookmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public abstract class GeneralException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private Object fieldValue;
    private String message;

    public GeneralException(String resourceName, String fieldName, Object fieldValue, String message) {
        super(message);
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.message = message;
    }
}
