package br.com.bookmanagement.service;

import br.com.bookmanagement.exception.AuthorNotFoundException;
import br.com.bookmanagement.exception.BookAlreadyExistsException;
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

    @Override
    public Book getEntityById(String id) {
        return repository.findById(id).orElseThrow(
                () -> new BookNotFoundException(CLASS_NAME, "id", id));
    }

    @Override
    public Book getEntityByParameter(String title) {
        return repository.findByTitle(title).orElseThrow(
                () -> new BookNotFoundException(CLASS_NAME, "title", title)
        );
    }

    public Book getBookByIsbn(String isbn) {
        return repository.findByIsbn(isbn).orElseThrow(
                () -> new BookNotFoundException(CLASS_NAME, "isbn", isbn)
        );
    }

    @Override
    public List<Book> getAllEntities() {
        return repository.findAll();
    }

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


//    public void addStudent(Student student) {
//        Boolean existsEmail = studentRepository
//                .selectExistsEmail(student.getEmail());
//        if (existsEmail) {
//            throw new BadRequestException(
//                    "Email " + student.getEmail() + " taken");
//        }
//
//        studentRepository.save(student);
//    }

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
        if(!repository.existsById(id)){
            throw new BookNotFoundException(CLASS_NAME, "id", id);
        }
        repository.deleteById(id);
    }

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
