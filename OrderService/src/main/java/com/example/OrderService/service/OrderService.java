package com.example.OrderService.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {

    // Method to retrieve all books
    public Object getBooks() {
        String url = "http://localhost:3030/books";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,
                new ParameterizedTypeReference<Object>() {
                    @Override
                    public Type getType() {
                        return super.getType();
                    }
                });
        Object book = response.getBody();
        return book;
    }

    // Method to order a book by its title
    public ResponseEntity<?> orderBookByTitle(String bookTitle) {
        String url = "http://localhost:3030/book/search?title=" + bookTitle;

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Object>() {
                        @Override
                        public Type getType() {
                            return super.getType();
                        }
                    });

            if (response.getStatusCode() == HttpStatus.OK) {
                // Book found
                Object book = response.getBody();
                String successMessage = "Order placed successfully for book: " + bookTitle;
                Map<String, Object> responseData = new HashMap<>();
                responseData.put("book", book);
                responseData.put("message", successMessage);
                return ResponseEntity.ok(responseData);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
            }
        } catch (HttpClientErrorException.NotFound ex) {
            // Microservice A returned a 404 error with the error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        } catch (Exception e) {
            // Other exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
