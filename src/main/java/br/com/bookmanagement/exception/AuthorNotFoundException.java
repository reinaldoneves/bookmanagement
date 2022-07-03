package br.com.bookmanagement.exception;

import lombok.Getter;

@Getter
public class AuthorNotFoundException extends GeneralException {

    public AuthorNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(resourceName,
                fieldName,
                fieldValue,
                String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
    }
}
