package br.com.bookmanagement.control;

import br.com.bookmanagement.exception.BookNotAvaiableException;
import br.com.bookmanagement.model.Book;
import br.com.bookmanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookmanagement")
public class BookController {

    private final BookService service;

    @Autowired
    public BookController(BookService bookService) {
        this.service = bookService;
    }

    @PutMapping("/update")
    public ResponseEntity<Book> updateBook(@RequestBody Book updatedBook) throws BookNotAvaiableException {
        Book book = null;
        try {
            book = service.updateEntity(updatedBook);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(book, HttpStatus.ACCEPTED);
    }

}