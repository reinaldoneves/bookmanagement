package br.com.bookmanagement.service;

import br.com.bookmanagement.exception.BookNotAvaiableException;
import br.com.bookmanagement.exception.BookNotFoundException;
import br.com.bookmanagement.model.Book;
import br.com.bookmanagement.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements GenericService<Book , BookRepository, String>{

    private final BookRepository repository;
    private final String CLASS_NAME = Book.class.getSimpleName();

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
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

    /**
     * Borrow a book from the libary if it is available
     * @param isbn the iternational standard book number
     **/
    public Book borrowABook(String isbn) {
        Book borrowBook = getBookByIsbn(isbn);
        if(!borrowBook.isAvailable()){
            throw new BookNotAvaiableException(CLASS_NAME, "isbn", isbn);
        }
        borrowBook.setAvailable(false);
        return repository.save(borrowBook);
    }

}
