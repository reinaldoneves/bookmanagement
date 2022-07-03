package br.com.bookmanagement.repo;

import br.com.bookmanagement.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findByTitle(String title);

    Optional<List<Book>>findAllByIsAvailable(Boolean isAvailable);

    Optional<List<Book>> findAllByAuthor(String author);

    Optional<Book> findByIsbn(String isbn);

    Boolean existsBookByIsbn(String isbn);
}
