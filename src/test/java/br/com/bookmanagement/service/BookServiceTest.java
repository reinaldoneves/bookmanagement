package br.com.bookmanagement.service;

import br.com.bookmanagement.model.Book;
import br.com.bookmanagement.repo.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private BookService underTest;

    @BeforeEach
    void setUp() {
        underTest = new BookService(bookRepository);
    }

    @Test
    @Disabled
    void getEntityById() {
    }

    @Test
    @Disabled
    void shouldGetBookByTitle() {

        String title = "The Lord of the rings: The fellowship of the ring";
        Optional<Book> mockBook = bookRepository.findByTitle(title);

        underTest.getEntityByParameter(mockBook.get().getTitle());

        assertThat(mockBook.get().getTitle()).isEqualTo(title);

    }

    @Test
    @Disabled
    void shouldGetBookByIsbn() {
    }

    @Test
    void shouldGetAllBooks() {
        //when
        underTest.getAllEntities();
        //then
        verify(bookRepository).findAll();
    }

    @Test
    @Disabled
    void shouldGetAllBooksAvailable() {
    }

    @Test
    @Disabled
    void shouldGetAllAuthorsBook() {
    }

    @Test
    @Rollback(false)
    @Disabled
    void shouldUpdateABook() {
        String bookName = "The Lord of the rings: The fellowship of the ring";
        Book mockBook = Book.builder()
                .isbn("0547951949")
                .author("J. R. R. Tolkien")
                .title("The Lord of the rings: The fellowship of the ring")
                .details("One Ring to rule them all, One Ring to find them, One Ring to bring them all and in the darkness bind them. In ancient times the Rings of Power were crafted by the Elven-smiths, and Sauron, the Dark Lord, forged the One Ring, filling it with his own power so that he could rule all others. But the One Ring was taken from him, and though he sought it throughout Middle-earth, it remained lost to him. After many ages it fell by chance into the hands of the hobbit Bilbo Baggins. From Sauron's fastness in the Dark Tower of Mordor, his power spread far and wide. Sauron gathered all the Great Rings to him, but always he searched for the One Ring that would complete his dominion. When Bilbo reached his eleventy-first birthday he disappeared, bequeathing to his young cousin Frodo the Ruling Ring and a perilous quest: to journey across Middle-earth, deep into the shadow of the Dark Lord, and destroy the Ring by casting it into the Cracks of Doom. The Lord of the Rings tells of the great quest undertaken by Frodo and the Fellowship of the Ring: Gandalf the Wizard; the hobbits Merry, Pippin, and Sam; Gimli the Dwarf; Legolas the Elf; Boromir of Gondor; and a tall, mysterious stranger called Strider.")
                .build();

        bookRepository.save(mockBook);
//        Optional<Book> updatedBook = given(bookRepository.findByTitle(bookName)).willReturn();

//        assertThat(updatedBook.get().getTitle()).isEqualTo(bookName);
    }

    @Test
    @Disabled
    void shouldDeleteABook() {
    }

}