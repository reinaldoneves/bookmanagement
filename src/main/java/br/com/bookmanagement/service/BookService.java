package br.com.bookmanagement.service;

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

}
