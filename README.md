# Low-Level Design (LLD) Practice 🚀

Welcome to my Low-Level Design (LLD) repository! This repository contains my implementation of foundational Object-Oriented Programming (OOP) concepts and SOLID principles, closely following the **Coder Army LLD Playlist**. 

The goal of this repository is to build a strong foundation in writing clean, scalable, maintainable, and production-ready code.

---

## 📂 Project Structure

The codebase is organized into modular Java files, each demonstrating a core architectural concept:

### 1. Object-Oriented Programming (OOP) Core Pillars
* **`Abstraction.java`**: Hiding complex implementation details and showing only essential features using abstract classes/interfaces.
* **`Encapsulation.java`**: Bundling data (variables) and methods together, protecting object state via access modifiers (`private`, `protected`).
* **`Inheritance.java`**: Demonstrating reusability by allowing child classes to inherit properties and behavior from parent classes.
* **`StaticPolymorphism.java`**: Compile-time polymorphism demonstrated via **Method Overloading**.
* **`DynamicPolymorphism.java`**: Runtime polymorphism demonstrated via **Method Overriding**.

### 2. SOLID Principles
* **Single Responsibility Principle (SRP)**
    * `SRPViolated.java`: An anti-pattern example where a single class (e.g., `ShoppingCart`) handles multiple distinct responsibilities like core logic and formatting/printing invoices.
    * `SRPFollowed.java`: The refactored version breaking down responsibilities into distinct, isolated classes.
* **Open/Closed Principle (OCP)**
    * `OCPViolated.java`: A scenario where adding a new feature (like a new car type) requires modifying existing class structures.
    * `OCPFollowed.java`: Refactored code designed to be **open for extension, but closed for modification** using abstractions.

---

## 🛠️ Tech Stack & Implementation Details

* **Language:** Java
* **Concepts Covered:** OOPs Pillars, SOLID Principles, Clean Code architecture.
* **Compilation Note:** Compiled bytecode target outputs (`.class` files like `Car.class`, `ElectricCar.class`, `ManualCar.class`) are generated during execution, demonstrating how inheritance handles compiled structures via standard decompilers (like FernFlower).

---
