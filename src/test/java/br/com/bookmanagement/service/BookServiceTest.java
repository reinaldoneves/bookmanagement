package br.com.bookmanagement.service;

import br.com.bookmanagement.repo.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private BookService underTest;

    @BeforeEach
    void setUp() {
        underTest = new BookService(bookRepository);
    }

    @Test
    void getEntityById() {
    }

    @Test
    void shouldGetBookByTitle() {
    }

    @Test
    void shouldGetBookByIsbn() {
    }

    @Test
    void shouldGetAllBooks() {
    }

    @Test
    void shouldGetAllBooksAvailable() {
    }

    @Test
    void shouldGetAllAuthorsBook() {
    }

    @Test
    void shouldCreateANewBook() {
    }

    @Test
    void shouldUpdateABook() {
    }

    @Test
    void shouldDeleteABook() {
    }
}