package br.com.bookmanagement.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
@Getter
public class BookNotAvaiableException extends GeneralException{

    public BookNotAvaiableException(String resourceName, String fieldName, Object fieldValue) {
        super(resourceName,
                fieldName,
                fieldValue,
                String.format("%s not available with %s : '%s'", resourceName, fieldName, fieldValue));
    }

}