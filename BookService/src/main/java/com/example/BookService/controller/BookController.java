package com.example.BookService.controller;

import com.example.BookService.entity.Book;
import com.example.BookService.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    // Endpoint to add a new book
    @PostMapping("/save")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    // Endpoint to get all books
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Endpoint to get a book by its ID
    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable long id) {
        return bookService.getBookById(id);
    }

    // Endpoint to search for a book by its title
    @GetMapping("/book/search")
    public ResponseEntity<?> getBookByTitle(@RequestParam String title) {
        Book book = bookService.getBookByTitle(title);
        if (book != null) {
            return ResponseEntity.ok(book);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
    }

    // Endpoint to update a book by its ID
    @PutMapping("/book/{id}")
    public Book updateBookById(@PathVariable long id, @RequestBody Book updatedBook) {
        return bookService.updateBookById(id, updatedBook);
    }

    // Endpoint to delete a book by its ID
    @DeleteMapping("/book/{id}")
    public String deleteBookById(@PathVariable long id) {
        return bookService.deleteBookById(id);
    }
}
