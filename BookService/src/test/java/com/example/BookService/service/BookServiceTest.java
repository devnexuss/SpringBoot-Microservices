package com.example.BookService.service;

import com.example.BookService.entity.Book;
import com.example.BookService.repository.IBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class BookServiceTest {

    @Mock
    IBookRepository repository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mocks
    }

    // Test case for addBook method
    @Test
    void addBookTest() {
        Book bookToAdd = new Book();

        given(repository.save(any(Book.class))).willReturn(bookToAdd);

        Book addedBook = bookService.addBook(bookToAdd);

        assertEquals(bookToAdd, addedBook); // Verifies that the added book matches the expected book
    }

    // Test case for getAllBooks method
    @Test
    void getAllBooksTest() {
        Book book = new Book();
        List<Book> expectedBooks = List.of(book);
        given(repository.findAll()).willReturn(expectedBooks);

        List<Book> actualBooks = bookService.getAllBooks();

        assertEquals(expectedBooks, actualBooks); // Verifies that the returned list of books matches the expected list
    }

    // Test case for getBookById method
    @Test
    void getBookByIdTest() {
        long bookId = 1L;
        Book expectedBook = new Book();
        expectedBook.setId(bookId);
        given(repository.findById(bookId)).willReturn(Optional.of(expectedBook));

        Book actualBook = bookService.getBookById(bookId);

        assertEquals(expectedBook, actualBook); // Verifies that the returned book matches the expected book
    }

    // Test case for getBookByTitle method when book exists
    @Test
    void getBookByTitleTestBookExists() {
        String title = "Existing Book Title";
        Book expectedBook = new Book();
        expectedBook.setTitle(title);
        given(repository.findAll()).willReturn(Collections.singletonList(expectedBook));

        Book actualBook = bookService.getBookByTitle(title);

        assertEquals(expectedBook, actualBook); // Verifies that the returned book matches the expected book
    }

    // Test case for getBookByTitle method when book does not exist
    @Test
    void getBookByTitleTestBookDoesNotExist() {
        String title = "Nonexistent Book Title";
        given(repository.findAll()).willReturn(Collections.emptyList());

        Book actualBook = bookService.getBookByTitle(title);

        assertEquals(null, actualBook); // Verifies that null is returned when the book does not exist
    }

    // Test case for updateBookById method
    @Test
    void updateBookIdTest() {
        long bookId = 1L;
        Book updatedBook = new Book();
        updatedBook.setId(bookId);
        given(repository.existsById(bookId)).willReturn(true);
        given(repository.save(any(Book.class))).willReturn(updatedBook);

        Book result = bookService.updateBookById(bookId, updatedBook);

        assertEquals(updatedBook, result); // Verifies that the returned book matches the updated book
    }

    // Test case for deleteBookById method
    @Test
    void deleteBookByIdTest() {
        long bookId = 1L;
        Book deletedBook = new Book();
        deletedBook.setId(bookId);
        given(repository.findById(bookId)).willReturn(Optional.of(deletedBook));

        String result = bookService.deleteBookById(bookId);

        assertEquals("Book deleted with id: " + bookId, result); // Verifies the deletion message
    }
}
