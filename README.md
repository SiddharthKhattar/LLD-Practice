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

## 🏆 LLD Interview Problem Rankings

Below is a comprehensive list of common LLD / Machine Coding interview problems, ranked in ascending order of importance and frequency in typical Tier-1 tech interviews. 

### 🔹 Niche or High-Level Design (HLD) Heavy (2.0 - 4.5)
*These problems are rarely asked in pure LLD or machine coding rounds because they either focus too heavily on system architecture (HLD) or are too narrow in scope.*

| Problem | Rating | Rationale |
| :--- | :---: | :--- |
| **Design Stack Overflow** | 2.0/10 | Too niche for LLD; often leans more into database schema design. |
| **Design CricInfo** | 3.0/10 | Focuses heavily on real-time data streaming and HLD rather than object-oriented principles. |
| **Design Traffic Signal Control System** | 3.0/10 | A bit too hardware/state-machine specific. |
| **Design LinkedIn** | 3.5/10 | Extremely HLD-focused (graph databases, recommendation engines). |
| **Design Course Registration System** | 3.5/10 | A very basic CRUD application that doesn't challenge design patterns enough. |
| **Design Coffee Vending Machine** | 4.0/10 | Redundant if you already know how to design a standard Vending Machine. |
| **Design Airline Management System** | 4.5/10 | Overly complex domain logic makes this hard to finish in a 45-minute LLD interview. |
| **Design an Online Auction System** | 4.5/10 | Focuses more on concurrency and locking (HLD concepts) than pure class structures. |

### 🔹 Foundational but Less Frequent (5.0 - 6.5)
*These are good for practicing basic Object-Oriented Programming (OOP) but are either slowly phasing out of modern interviews or are usually asked as HLD questions.*

| Problem | Rating | Rationale |
| :--- | :---: | :--- |
| **Design ATM** | 5.0/10 | A classic State/Chain of Responsibility pattern problem, but slightly outdated. |
| **Design a Social Network (Facebook)** | 5.0/10 | Primarily an HLD question, though designing Post/Comment models is good basic practice. |
| **Design Hotel Management System** | 5.5/10 | Standard entity modeling, but less common than booking a specific ticket/seat. |
| **Design Music Streaming (Spotify)** | 5.5/10 | Good for state management (Play/Pause), but audio chunking is HLD. |
| **Design a Task Management System** | 6.0/10 | Good practice for the Command pattern, but lacks the complexity tier-1 companies want. |
| **Design Car Rental System** | 6.0/10 | A solid inventory management problem, but slightly easier than modern equivalents. |
| **Design Online Stock Brokerage** | 6.0/10 | Excellent for the Observer pattern, but financial domain logic can distract from pure design. |
| **Design a Digital Wallet Service** | 6.5/10 | Great for practicing concurrency and thread safety; core OOP design is relatively simple. |
| **Design Restaurant Management System** | 6.5/10 | A simplified version of Food Delivery; usually skipped in favor of Swiggy/Zomato. |

### 🔹 Highly Relevant & Common (7.0 - 8.5)
*You should expect these in modern machine coding rounds. They perfectly balance entity modeling, design patterns, and algorithmic thinking.*

| Problem | Rating | Rationale |
| :--- | :---: | :--- |
| **Design Pub Sub System** | 7.0/10 | The ultimate test for the Observer pattern and concurrent queue management. |
| **Design Logging Framework** | 7.5/10 | The standard way companies test your knowledge of Chain of Responsibility and Singleton. |
| **Design a Library Management System** | 7.5/10 | The quintessential basic OOP interview question. Expect this in early-career interviews. |
| **Design Ride-Sharing (Uber)** | 7.5/10 | Tests location-based matching algorithms alongside driver/rider state machines. |
| **Design Online Shopping (Amazon)** | 7.5/10 | Massive scope, but tests core concepts like Cart, Checkout, and Payment interfaces. |
| **Design a Vending Machine** | 8.0/10 | The absolute gold standard for testing the **State Design Pattern**. |
| **Design a Concert Ticket System** | 8.0/10 | A great concurrency challenge (handling simultaneous bookings). |
| **Design LRU Cache** | 8.5/10 | Extremely frequent. Tests combining Data Structures (HashMap + Doubly Linked List) cleanly. |
| **Design Tic Tac Toe Game** | 8.5/10 | The most common entry-level LLD question. Tests extensibility (e.g., N x N grids instead of 3 x 3). |

### 🔥 The Absolute Classics / "Must-Knows" (9.0 - 10.0)
*If you are preparing for LLD at Tier-1 tech companies, you must be able to code these flawlessly. They are asked constantly.*

| Problem | Rating | Rationale |
| :--- | :---: | :--- |
| **Design an Elevator System** | 9.0/10 | The ultimate test of state management, direction algorithms (e.g., SCAN), and concurrent requests. |
| **Design a Snake and Ladder game** | 9.0/10 | A staple board game question testing entity encapsulation and cleanly decoupled game loops. |
| **Design Movie Ticket Booking** | 9.5/10 | Often framed as "BookMyShow." Tests locking mechanisms, seat mapping, payments, and concurrency. |
| **Design Food Delivery (Swiggy)** | 9.5/10 | Combines complex state machines (Order Status) with the Strategy pattern (routing, pricing). |
| **Design Parking Lot** | 10.0/10 | **The #1 most asked LLD question globally.** Tests hierarchy, extensibility, Factory, and Strategy patterns. |
| **Design Chess Game** | 10.0/10 | The gold standard of OOP. Tests deep polymorphism, game state management, and validation logic. |
| **Design Splitwise** | 10.0/10 | A heavily requested FAANG problem. Tests user management, expense splitting algorithms, and graph simplification. |