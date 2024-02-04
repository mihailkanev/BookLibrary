# Library Management System API

## Overview

This is a RESTful API for managing a library's book inventory. It provides various endpoints to perform CRUD (Create, Read, Update, Delete) operations on books and authors.

## Endpoints

### 1. Get All Books

- **Endpoint:** `/api/books`
- **Method:** GET
- **Response:** List of books in JSON format
- **Description:** Retrieve a list of all books available in the library.

### 2. Get Book by ID

- **Endpoint:** `/api/books/{id}`
- **Method:** GET
- **Parameters:**
  - `{id}`: Book ID
- **Response:** Book details in JSON format or 404 if not found
- **Description:** Retrieve detailed information about a specific book using its ID.

### 3. Add New Book

- **Endpoint:** `/api/books`
- **Method:** POST
- **Request Body:** Book details in JSON format
- **Response:** Success message or error details
- **Description:** Add a new book to the library inventory.

### 4. Update Book by ID

- **Endpoint:** `/api/books/{id}`
- **Method:** PUT
- **Parameters:**
  - `{id}`: Book ID
- **Request Body:** Updated book details in JSON format
- **Response:** Success message or error details
- **Description:** Update information about a specific book using its ID.

### 5. Delete Book by ID

- **Endpoint:** `/api/books/{id}`
- **Method:** DELETE
- **Parameters:**
  - `{id}`: Book ID
- **Response:** Success message or error details
- **Description:** Delete a book from the library inventory using its ID.

### 6. Get All Authors

- **Endpoint:** `/api/authors`
- **Method:** GET
- **Response:** List of authors in JSON format
- **Description:** Retrieve a list of all authors in the library.

### 7. Get Author by ID

- **Endpoint:** `/api/authors/{id}`
- **Method:** GET
- **Parameters:**
  - `{id}`: Author ID
- **Response:** Author details in JSON format or 404 if not found
- **Description:** Retrieve detailed information about a specific author using their ID.

### 8. Add New Author

- **Endpoint:** `/api/authors`
- **Method:** POST
- **Request Body:** Author details in JSON format
- **Response:** Success message or error details
- **Description:** Add a new author to the library records.

### 9. Update Author by ID

- **Endpoint:** `/api/authors/{id}`
- **Method:** PUT
- **Parameters:**
  - `{id}`: Author ID
- **Request Body:** Updated author details in JSON format
- **Response:** Success message or error details
- **Description:** Update information about a specific author using their ID.

### 10. Delete Author by ID

- **Endpoint:** `/api/authors/{id}`
- **Method:** DELETE
- **Parameters:**
  - `{id}`: Author ID
- **Response:** Success message or error details
- **Description:** Delete an author from the library records using their ID.

