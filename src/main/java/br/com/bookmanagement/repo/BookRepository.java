package br.com.bookmanagement.repo;

import br.com.bookmanagement.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    /**
     * 1) Lista de livros
     * - Serviço para pesquisar livros (nome, autor, etc)
     * - Serviço para exibir detalhes de um livro
     * - Serviço para permitir alugar um livro
     * - Não permitir alugar um livro já alugado
     *
     * 2) CRUD de livros
     * - Serviço para cadastro, edição e remoção de livros
     * - Não deve ser possível editar e remover livros que estão alugados
     *
     * */

    Optional<Book> findByTitle(String title);

    Optional<Book> findByAuthor(String name);

    Optional<List<Book>> findAllByAuthor(String author);

}
