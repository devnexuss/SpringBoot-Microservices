package com.example.OrderService.controller;

import com.example.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;


@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    // Endpoint to retrieve all books
    @GetMapping("/getBooks")
    public Object getBooks() {
        return orderService.getBooks();
    }

    // Endpoint to order a book by its title
    @GetMapping("/orderBook")
    public Object orderBookByTitle(@RequestParam String bookTitle) {
        // Call the service method to get the book
        return orderService.orderBookByTitle(bookTitle);
    }
}
