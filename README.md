# Microservices Architecture with Spring Boot

## Overview

This project implements a microservices architecture using Spring Boot, consisting of two separate services, namely the **BookService** and **OrderService**. These services communicate with each other via RESTful APIs, following the principles of microservices architecture.

- **Microservice A (BookService)**: This microservice is responsible for implementing CRUD (Create, Read, Update, Delete) operations for the 'Book' entity. It allows users to create, retrieve, update, and delete book records within its database.

- **Microservice B (OrderService)**: In this microservice, the focus is on facilitating order placement based on user requests. Microservice B communicates with Microservice A to determine if a requested book exists. If the book is found in Microservice A, the order is successfully placed. Otherwise, the user receives a 'Book Not Found' response.

## Features
- **Swagger Documentation:** Both Microservice A (BookService) and Microservice B (OrderService) include Swagger documentation for easy understanding and testing of the APIs.

- **Test Cases:** Comprehensive test cases have been included for both Microservice A and Microservice B to ensure the correctness and reliability of the functionalities.

## Test Cases

### Microservice A Test Cases

- **BookServiceTest**: Validation scenarios conducted at the service layer of Microservice A, ensuring the correctness and reliability of its functionalities related to book management.

- **BookControllerTest**: Validation scenarios conducted at the controller layer of Microservice A, simulating HTTP requests and responses for each endpoint of the BookController class.

### Microservice B Test Cases

- **OrderServiceTest**: Validation scenarios conducted at the service layer of Microservice B, ensuring the correctness and reliability of its functionalities related to order management and interactions with Microservice A.

- **OrderControllerTest**: Validation scenarios conducted at the controller layer of Microservice B, simulating HTTP requests and responses for each endpoint of the OrderController class.

## Conclusion

This project demonstrates the implementation of a microservices architecture using Spring Boot, emphasizing communication between services via RESTful APIs. It showcases developing, and testing microservices, ensuring scalability, reliability, and maintainability of the system.