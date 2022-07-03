package br.com.bookmanagement.service;

import br.com.bookmanagement.exception.AuthorNotFoundException;
import br.com.bookmanagement.exception.BookAlreadyExistsException;
import br.com.bookmanagement.exception.BookNotAvaiableException;
import br.com.bookmanagement.exception.BookNotFoundException;
import br.com.bookmanagement.model.Book;
import br.com.bookmanagement.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    /**
     * Borrow a book from the libary if it is available
     * @param isbn the iternational standard book number
     **/
    public Book borrowABook(String isbn) {
        Book borrowdBook = getBookByIsbn(isbn);
        if(!borrowdBook.isAvailable()){
            throw new BookNotAvaiableException(CLASS_NAME, "isbn", isbn);
        }
        borrowdBook.setAvailable(false);
        return borrowdBook;
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
     * Update a given book if it is available
     * @param updatedBook the book to be updated
     **/
    @Override
    public Book updateEntity(Book updatedBook) {
        if(!updatedBook.isAvailable()){
            throw new BookNotAvaiableException(CLASS_NAME, "Title", updatedBook.getTitle());
        }
        return repository.save(updatedBook);
    }

    /**
     * Delete a given book
     * if the book is not available, then it can't be removed
     * @param id the id of the book to be deleted
     */
    @Override
    public void deleteEntity(String id) {
        if(!repository.existsById(id)){
            throw new BookNotFoundException(CLASS_NAME, "id", id);
        }
        repository.deleteById(id);
    }

    /**
     * Verifies if a book exists based on its internation standard book number
     * @param isbn
     * */
    private boolean doesExists(String isbn) {
        Boolean existsByIsbn = repository.selectExistsIsbn(isbn);

        if (existsByIsbn) {
            throw new BookAlreadyExistsException(CLASS_NAME,
                    "isbn",
                    isbn);
        }

        return false;
    }
}
