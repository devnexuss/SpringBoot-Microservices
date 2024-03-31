package com.example.BookService.contoller;

import com.example.BookService.controller.BookController;
import com.example.BookService.entity.Book;
import com.example.BookService.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mocks
        mockMvc = standaloneSetup(bookController).build();
    }

    // Test case for addBook endpoint
    @Test
    void addBookShouldReturnSavedBook() throws Exception {
        Book book = new Book();
        book.setTitle("Sample Book Title");
        given(bookService.addBook(book)).willReturn(book);

        mockMvc.perform(post("/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Sample Book Title\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"title\":\"Sample Book Title\"}"));
    }

    // Test case for getAllBooks endpoint
    @Test
    void getAllBooksShouldReturnAllBooks() throws Exception {
        Book book = new Book(1L, "Sample Book Title", "John Doe", "1234567890", 19.99, "Fiction");
        List<Book> books = List.of(book);

        given(bookService.getAllBooks()).willReturn(books);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"title\":\"Sample Book Title\",\"author\":\"John Doe\",\"isbn\":\"1234567890\",\"price\":19.99,\"category\":\"Fiction\"}]"));
    }

    // Test case for getBookById endpoint
    @Test
    void getBookByIdReturnsBookDetails() throws Exception {
        Book book = new Book(1L, "Sample Book Title", "John Doe", "1234567890", 19.99, "Fiction");
        given(bookService.getBookById(1L)).willReturn(book);

        mockMvc.perform(get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"title\":\"Sample Book Title\",\"author\":\"John Doe\",\"isbn\":\"1234567890\",\"price\":19.99,\"category\":\"Fiction\"}"));
    }

    // Test case for getBookByTitle endpoint when book exists
    @Test
    void getBookByTitleBookExists() throws Exception {
        Book book = new Book();
        book.setTitle("Sample Book Title");
        given(bookService.getBookByTitle("Sample Book Title")).willReturn(book);

        mockMvc.perform(get("/book/search?title=Sample Book Title"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"title\":\"Sample Book Title\"}"));

    }

    // Test case for getBookByTitle endpoint when book does not exist
    @Test
    void getBookByTitleBookDoesNotExist() throws Exception {
        given(bookService.getBookByTitle(anyString())).willReturn(null);

        mockMvc.perform(get("/book/search?title=Nonexistent Book Title"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Book not found"));
    }

    // Test case for updateBookById endpoint
    @Test
    void updateBookByIdReturnsUpdatedBookDetails() throws Exception {
        Book updatedBook = new Book(1L, "Updated Book Title", "John Doe", "1234567890", 23.00, "Fiction");

        given(bookService.updateBookById(1L, updatedBook)).willReturn(updatedBook);

        mockMvc.perform(put("/book/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"title\": \"Updated Book Title\", \"author\": \"John Doe\", \"isbn\": \"1234567890\", \"price\": 23.00, \"category\": \"Fiction\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"title\":\"Updated Book Title\",\"author\":\"John Doe\",\"isbn\":\"1234567890\",\"price\":23.00,\"category\":\"Fiction\"}"));
    }

    // Test case for deleteBookById endpoint
    @Test
    void deleteBookByIdReturnsSuccessMessage() throws Exception {
        given(bookService.deleteBookById(1L)).willReturn("Book deleted with id: 1");

        mockMvc.perform(delete("/book/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book deleted with id: 1"));
    }

}
