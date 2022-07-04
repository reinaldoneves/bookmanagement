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
     * Update a given book if it is available
     * @param updatedBook the book to be updated
     **/
    @Override
    public Book updateEntity(Book updatedBook) throws BookNotFoundException, BookNotAvaiableException {
        if(!repository.existsById(updatedBook.getId())){
            throw new BookNotFoundException(CLASS_NAME, "Title", updatedBook.getTitle());
        }
        if(!updatedBook.isAvailable()){
            throw new BookNotAvaiableException(CLASS_NAME, "Title", updatedBook.getTitle());
        }
        return repository.save(updatedBook);
    }

}
