package br.com.bookmanagement.service;

import br.com.bookmanagement.exception.AuthorNotFoundException;
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

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book getEntityById(String id) {
        return repository.findById(id).orElseThrow(
                () -> new BookNotFoundException("Book", "id", id));
    }

    @Override
    public Book getEntityByParameter(String title) {
        return repository.findByTitle(title).orElseThrow(
                () -> new BookNotFoundException("Book", "title", title)
        );
    }

    public Book getBookByIsbn(String isbn) {
        return repository.findByIsbn(isbn).orElseThrow(
                () -> new BookNotFoundException("Book", "isbn", isbn)
        );
    }

    @Override
    public List<Book> getAllEntities() {
        return repository.findAll();
    }

    public List<Book> getAllBooksAvailable(Boolean isAvailable) {
        return repository.findAllByIsAvailable(isAvailable).orElseThrow(
                () -> new BookNotFoundException("Book", "available", null)
        );
    }

    /**
     * Get all books by an author
     * @param author the book writer
     **/
    @Override
    public List<Book> getAllEntitiesByParameter(String author) {
        return repository.findAllByAuthor(author).orElseThrow(
                () -> new AuthorNotFoundException("Book", "author", author)
        );
    }

    /**
     * Create a new book
     * @param newBook the new book
     **/
    @Override
    public Book createEntity(Book newBook) {
        return repository.save(newBook);
    }

    /**
     * Update a given book
     * @param updatedBook the book to be updated
     **/
    @Override
    public Book updateEntity(Book updatedBook) {
        return repository.save(updatedBook);
    }

    /**
     * Delete a given book
     * if the book is not available, then it can't be removed
     * @param id the id of the book to be deleted
     */
    @Override
    public void deleteEntity(String id) {
        validateBook(id);
        repository.deleteById(id);
    }

    /**
     *TODO: Implement business logic
     **/
    private void validateBook(String id) {
        Optional<Book> book = repository.findById(id);
        if (book.isEmpty()) {}
    }
}
