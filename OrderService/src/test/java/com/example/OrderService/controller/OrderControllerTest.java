package com.example.OrderService.controller;

import com.example.OrderService.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        mockMvc = standaloneSetup(orderController).build();
    }

    // Test case for getBooks method
    @Test
    void getBooksShouldReturnAllBooks() throws Exception {
        // Perform GET request to /getBooks endpoint and expect HTTP status 200 (OK)
        mockMvc.perform(get("/getBooks"))
                .andExpect(status().isOk());
    }

    @Test
    void orderBookByTitleShouldReturnBookDetailsAndMessage() throws Exception {
        // Define the book title for the test
        String bookTitle = "Sample Book";

        // Perform GET request to /orderBook endpoint and expect HTTP status 200 (OK)
        mockMvc.perform(MockMvcRequestBuilders.get("/orderBook")
                        .param("bookTitle", bookTitle))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void orderBookByTitleShouldReturnNotFoundWhenBookNotFound() throws Exception {
        // Mock the behavior of the OrderService to return a ResponseEntity with status NOT_FOUND and message "Book not found"
        doReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found"))
                .when(orderService)
                .orderBookByTitle(anyString());

        // Perform GET request to /orderBook endpoint with a book title parameter and expect HTTP status 404 (Not Found)
        String bookTitle = "Nonexistent Book";
        mockMvc.perform(get("/orderBook")
                        .param("bookTitle", bookTitle)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Book not found"));
    }
}
