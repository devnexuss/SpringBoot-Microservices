package com.example.BookService.service;

import com.example.BookService.entity.Book;
import com.example.BookService.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired // Injects an instance of IBookRepository
    IBookRepository repository;

    // Method to add a new book
    public Book addBook(Book book) {
        return repository.save(book);
    }

    // Method to retrieve all books
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    // Method to retrieve a book by its ID
    public Book getBookById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Method to retrieve a book by its title
    public Book getBookByTitle(String title) {
        List<Book> books = repository.findAll();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Method to update a book by its ID
    public Book updateBookById(Long id, Book updatedBook) {
        if (repository.existsById(id)) {
            updatedBook.setId(id);
            return repository.save(updatedBook);
        } else {
            return null;
        }
    }

    // Method to delete a book by its ID
    public String deleteBookById(Long id) {
        Optional<Book> bookOptional = repository.findById(id);
        if (bookOptional.isPresent()) {
            repository.deleteById(id);
            return "Book deleted with id: " + id;
        } else {
            return "Book not found";
        }
    }
}
