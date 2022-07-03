package br.com.bookmanagement.service;

import br.com.bookmanagement.exception.AuthorNotFoundException;
import br.com.bookmanagement.exception.BookNotFoundException;
import br.com.bookmanagement.model.Book;
import br.com.bookmanagement.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements GenericService<Book , BookRepository, String>{

    private final BookRepository repository;
    private final String CLASS_NAME = Book.class.getSimpleName();

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    /**
     * Get a book by internal identifier
     * @param id the book identifier
     **/
    @Override
    public Book getEntityById(String id) {
        return repository.findById(id).orElseThrow(
                () -> new BookNotFoundException(CLASS_NAME, "id", id));
    }

    /**
     * Get a book by its Title
     * @param title the book's Title
     **/
    @Override
    public Book getEntityByParameter(String title) {
        return repository.findByTitle(title).orElseThrow(
                () -> new BookNotFoundException(CLASS_NAME, "title", title)
        );
    }

    /**
     * Get a book by ISBN
     * @param isbn the iternational standard book number
     **/
    public Book getBookByIsbn(String isbn) {
        return repository.findByIsbn(isbn).orElseThrow(
                () -> new BookNotFoundException(CLASS_NAME, "isbn", isbn)
        );
    }

    @Override
    public List<Book> getAllEntities() {
        return repository.findAll();
    }

    /**
     * Get all books by isAvailable status
     * @param isAvailable the status of the book
     **/
    public List<Book> getAllBooksAvailable(Boolean isAvailable) {
        return repository.findAllByIsAvailable(isAvailable).orElseThrow(
                () -> new BookNotFoundException(CLASS_NAME, "available", null)
        );
    }

    /**
     * Get all books by an author
     * @param author the books writer
     **/
    @Override
    public List<Book> getAllEntitiesByParameter(String author) {
        return repository.findAllByAuthor(author).orElseThrow(
                () -> new AuthorNotFoundException(CLASS_NAME, "author", author)
        );
    }

}
