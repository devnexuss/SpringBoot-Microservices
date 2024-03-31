package com.example.BookService.repository;

import com.example.BookService.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface extending JpaRepository for CRUD operations on Book entities
public interface IBookRepository extends JpaRepository<Book, Long> {
}
