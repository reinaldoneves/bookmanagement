package br.com.bookmanagement.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class BookNotFoundException extends GeneralException{

    public BookNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(resourceName,
                fieldName,
                fieldValue,
                String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }

}