package com.example.OrderService.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Initializes mocks
    }

    // Test case for getBooks method
    @Test
    public void testGetBooks() {
        // Mock successful response from Microservice A
        Object mockBooks = Arrays.asList(
                new LinkedHashMap<String, Object>() {{
                    put("id", 1);
                    put("title", "Harry Potter and the Sorcerer’s Stone");
                    put("author", "J.K. Rowling");
                    put("isbn", "9780590353427");
                    put("price", 13.0);
                    put("category", "Fiction");
                }},
                new LinkedHashMap<String, Object>() {{
                    put("id", 2);
                    put("title", "Love & Gelato");
                    put("author", "Jenna Evans Welch");
                    put("isbn", "9781481432559");
                    put("price", 18.11);
                    put("category", "Romance");
                }},
                new LinkedHashMap<String, Object>() {{
                    put("id", 3);
                    put("title", "Stargirl");
                    put("author", "Jerry Spinelli");
                    put("isbn", "9780439488402");
                    put("price", 7.87);
                    put("category", "Fiction");
                }}
        );

        // Perform the operation
        Object result = orderService.getBooks();

        // Verify the result
        assertEquals(mockBooks, result);
    }

    // Test case for orderBookByTitle method with success
    @Test
    public void testOrderBookByTitleSuccess() {
        // Mock successful response from Microservice A
        String bookTitle = "Stargirl";
        Map<String, Object> mockBook = new HashMap<>();
        mockBook.put("id", 3);
        mockBook.put("title", "Stargirl");
        mockBook.put("author", "Jerry Spinelli");
        mockBook.put("isbn", "9780439488402");
        mockBook.put("price", 7.87);
        mockBook.put("category", "Fiction");

        // Perform the order
        ResponseEntity<?> response = orderService.orderBookByTitle(bookTitle);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Map); // Check if the response body is a map
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        assertEquals("Order placed successfully for book: " + bookTitle, responseBody.get("message"));
        assertEquals(mockBook, responseBody.get("book"));
    }

    // Test case for orderBookByTitle method when book is not found
    @Test
    public void testOrderBookByTitleBookNotFound() {
        // Mock 404 response from Microservice A
        String bookTitle = "Non-existing Book";

        // Perform the order
        ResponseEntity<?> response = orderService.orderBookByTitle(bookTitle);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Book not found", response.getBody());
    }
}

