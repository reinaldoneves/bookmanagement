package br.com.bookmanagement.control;

import br.com.bookmanagement.model.Book;
import br.com.bookmanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmanagement")
public class BookController {

    private final BookService service;

    @Autowired
    public BookController(BookService bookService) {
        this.service = bookService;
    }

    @GetMapping("/find/ByIsbn/{isbn}")
    public ResponseEntity<Book> getByIsbn(@PathVariable("isbn") String isbn){
        Book book = service.getBookByIsbn(isbn);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/borrow/{isbn}")
    public ResponseEntity<Book> borrowABook(@PathVariable("isbn") String isbn){
        //TODO: should be a put method
        Book book = service.borrowABook(isbn);
        return new ResponseEntity<>(book, HttpStatus.ACCEPTED);
    }

}
