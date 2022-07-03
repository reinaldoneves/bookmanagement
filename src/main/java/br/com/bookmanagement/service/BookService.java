package br.com.bookmanagement.service;

import br.com.bookmanagement.exception.BookAlreadyExistsException;
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
     * Create a new book
     * @param newBook the new book
     **/
    @Override
    public Book createEntity(Book newBook) throws BookAlreadyExistsException {
        if(doesExists(newBook.getIsbn())){
            throw new BookAlreadyExistsException(CLASS_NAME, "isbn", newBook.getIsbn());
        }
        return repository.save(newBook);
    }

    /**
     * Verifies if a book exists based on its internation standard book number
     * @param isbn
     * */
    private boolean doesExists(String isbn) {
        Boolean existsByIsbn = repository.existsBookByIsbn(isbn);

        if (existsByIsbn) {
            throw new BookAlreadyExistsException(CLASS_NAME,
                    "isbn",
                    isbn);
        }

        return false;
    }

}
