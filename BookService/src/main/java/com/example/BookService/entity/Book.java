package com.example.BookService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private long id; // Unique identifier for the book
    private String title; // Title of the book
    private String author; // Author of the book
    private String isbn; // ISBN of the book
    private double price; // Price of the book
    private String category; // Category of the book
}
