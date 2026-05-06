# Product Registration System

## Overview
This project is a refactored version of my first console-based product registration system, also developed in Java.

It was built to apply my current Java skills, this time introducing OOP concepts such as class separation, encapsulation, and a service layer to organize responsibilities across the application.

The app allows users to register, list, search, edit, and delete products through a simple terminal menu. Although simple, it reflects core programming structures commonly used in real-world applications.

---

## Features

### Product Registration
- Register new products with name, price, and quantity
- Input validation for all fields
- Prevents duplicate product names

### Product Listing
- Displays all registered products in a formatted table
- Shows a message if no products are registered

### Product Search
- Search products by name (case-insensitive)
- Displays product information if found

### Product Edit
- Search for a product by name
- Edit price and quantity
- Displays current data before editing

### Product Delete
- Search for a product by name
- Displays product information before deletion
- Confirmation prompt to prevent accidental deletion

### Exit
- Confirmation prompt before closing the application

---

## Concepts Practiced
- Object-Oriented Programming (classes, encapsulation, getters/setters)
- Service layer pattern (separation of business logic from UI)
- Collections (`ArrayList`, `List` interface)
- Input validation and user input handling with `Scanner`
- Console output formatting with `printf`
- Control flow (if/else, while, for-each)

---

## Technologies
- Java 25
- IntelliJ IDEA
- Console-based application

---

## How to Run
1. Clone the repository
2. Open the project in IntelliJ IDEA
3. Run `ProductRegistrationApp.java`
