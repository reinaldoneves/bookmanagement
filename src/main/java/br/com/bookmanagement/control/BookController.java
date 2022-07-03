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

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book newBook){
        Book book = service.createEntity(newBook);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

}