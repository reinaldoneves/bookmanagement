package br.com.bookmanagement.exception;

public class BookAlreadyExistsException extends GeneralException{

    public BookAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
        super(resourceName,
                fieldName,
                fieldValue,
                String.format("%s already exists with %s : %s", resourceName, fieldName, fieldValue));
    }
}
