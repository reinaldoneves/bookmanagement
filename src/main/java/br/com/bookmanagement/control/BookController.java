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

    @GetMapping("/all/available/{isAvailable}")
    public ResponseEntity<List<Book>> getAllBooksAvailable(@PathVariable("isAvailable") Boolean isAvailable){
        List<Book> allBooksAvailable = service.getAllBooksAvailable(isAvailable);
        return new ResponseEntity<>(allBooksAvailable, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> allBooks = service.getAllEntities();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") String id){
        Book book = service.getEntityById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/find/ByIsbn/{isbn}")
    public ResponseEntity<Book> getByIsbn(@PathVariable("isbn") String isbn){
        Book book = service.getBookByIsbn(isbn);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/find/ByAuthor/{author}")
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable("author") String author){
        List<Book> allThatAuthorsBooks = service.getAllEntitiesByParameter(author);
        return new ResponseEntity<>(allThatAuthorsBooks, HttpStatus.OK);
    }
    @GetMapping("/find/ByTitle/{title}")
    public ResponseEntity<Book> getBooksByTitle(@PathVariable("title") String title){
        Book book  = service.getEntityByParameter(title);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book newBook){
        Book book = service.createEntity(newBook);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Book> updateBook(@RequestBody Book updatedBook){
        Book book = service.updateEntity(updatedBook);
        return new ResponseEntity<>(book, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") String id){
        service.deleteEntity(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
