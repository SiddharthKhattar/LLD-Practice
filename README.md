# Low-Level Design (LLD) Practice 🚀

Welcome to my Low-Level Design (LLD) repository! This repository contains my implementation of foundational Object-Oriented Programming (OOP) concepts, SOLID principles, Design Patterns, and comprehensive machine-coding projects, closely following the **Coder Army LLD Playlist**. 

The goal of this repository is to build a strong foundation in writing clean, scalable, maintainable, and production-ready code.

---

## 📂 Repository Structure

The codebase is organized into four main modular directories, each demonstrating core architectural concepts, complete with code implementations and visual diagrams.

### 1. 🏛️ Four Pillars of OOPS (`/FourPillarsOOPS`)
Core Object-Oriented Programming foundations demonstrating how to structure data and behavior.
* **`Abstraction.java`**: Hiding complex implementation details and showing only essential features.
* **`Encapsulation.java`**: Bundling data and methods together, protecting object state via access modifiers.
* **`Inheritance.java`**: Demonstrating reusability by allowing child classes to inherit properties from parents.
* **`StaticPolymorphism.java` & `DynamicPolymophism.java`**: Compile-time (Method Overloading) and Runtime (Method Overriding) polymorphism.

### 2. 🧱 SOLID Principles (`/SOLID`)
Each principle is demonstrated using a "Violated" (anti-pattern) and "Followed" (refactored) approach.
* **Single Responsibility (SRP)**: Breaking down monolithic classes into single-purpose components.
* **Open/Closed (OCP)**: Designing systems to be open for extension but closed for modification.
* **Liskov Substitution (LSP)** *(Folder: `LinkovSubstitution`)*: Deep dive into substituting child classes without breaking the system. Includes specific sub-rules for Methods, Properties, and Signatures (e.g., Pre/Post conditions, Class invariants).
* **Interface Segregation (ISP)**: Preventing fat interfaces by creating smaller, role-specific interfaces.
* **Dependency Inversion (DIP)**: Depending on abstractions (interfaces) rather than concrete implementations.

### 3. 🧩 Design Patterns

| Creational Patterns | Structural Patterns | Behavioral Patterns |
| :--- | :--- | :--- |
| [Singleton](./DesignPatterns/1_CreationalPatterns/Singleton) | [Adapter](./DesignPatterns/2_StructuralPatterns/Adapter) | [Iterator](./DesignPatterns/3_BehavioralPatterns/Iterator) |
| [Factory Method](./DesignPatterns/1_CreationalPatterns/FactoryMethod) | [Bridge](./DesignPatterns/2_StructuralPatterns/Bridge) | [Observer](./DesignPatterns/3_BehavioralPatterns/Observer) |
| [Abstract Factory](./DesignPatterns/1_CreationalPatterns/AbstractFactory) | [Composite](./DesignPatterns/2_StructuralPatterns/Composite) | [Strategy](./DesignPatterns/3_BehavioralPatterns/Strategy) |
| [Builder](#) | [Decorator](#) | [Command](./DesignPatterns/3_BehavioralPatterns/Command) |
| [Prototype](#) | [Facade](./DesignPatterns/2_StructuralPatterns/Facade) | [State](./DesignPatterns/3_BehavioralPatterns/StateDesignAndVendingMachine) |
| | [Flyweight](#) | [Template Method](./DesignPatterns/3_BehavioralPatterns/Template) |
| | [Proxy](./DesignPatterns/2_StructuralPatterns/Proxy) | [Visitor](#) |
| | | [Mediator](./DesignPatterns/3_BehavioralPatterns/MediatorPlusChatRoom) |
| | | [Memento](./DesignPatterns/3_BehavioralPatterns/Memento) |
| | | [Chain of Responsibility](./DesignPatterns/3_BehavioralPatterns/ChainOfResponsibility) |


### 4. 🛠️ LLD Projects & Case Studies (`/Projects`)
End-to-end Low-Level Design implementations of real-world systems and games.
* **[`Chess`](./Projects/Chess)**: Full object-oriented breakdown of a standard chess game.
* **[`DocumentEditor`](./Projects/DocumentEditor)**: A comparative look at `BadDesign` vs. `GoodDesign` when building a text editor.
* **[`NotificationSystem`](./Projects/NotificationSystem)**: A robust alert engine utilizing Observer, Strategy, and Decorator patterns (Email, SMS, PopUp).
* **[`PaymentGatewaySystem`](./Projects/PaymentGatewaySystem)**: Handling transactions securely and modularly.
* **[`SnakesAndLadders`](./Projects/SnakesAndLadders)** & **[`TicTacToe`](./Projects/TicTacToe)**: Classic board game logic emphasizing grid scalability and decoupled game loops.
* **[`SplitwiseApp`](./Projects/SplitwiseApp)**: Implementation of the user expense splitting application.
* **[`Spotify`](./Projects/Spotify)**: An audio streaming system design featuring device management, playback strategies, and audio engines.
* **[`Tinder`](./Projects/Tinder)**: A structural clone implementation of the matching application.
* **[`ZomatoFoodDeliveryApp`](./Projects/ZomatoFoodDeliveryApp)**: A complex, multi-layered monolithic application structure including models, managers, factories, services, and strategies for processing food orders (Credit Card, UPI).

---

## ⚙️ Tech Stack & Implementation Details

* **Language:** Java
* **Concepts Covered:** OOPs Pillars, SOLID Principles, Design Patterns, Clean Code Architecture.
* **Visuals:** `.excalidraw` and `.png` files are included across the repository to provide UML and class-diagram breakdowns before the code is written.
* **Compilation Note:** Compiled bytecode target outputs (`.class` files) are generated during execution, demonstrating how inheritance and patterns handle compiled structures.

---

## 🏆 LLD Interview Problem Rankings

Below is a comprehensive list of common LLD / Machine Coding interview problems, ranked in ascending order of importance and frequency in typical Tier-1 tech interviews. 

### 🔹 Niche or High-Level Design (HLD) Heavy (2.0 - 4.5)
*These problems are rarely asked in pure LLD or machine coding rounds because they either focus too heavily on system architecture (HLD) or are too narrow in scope.*

| Problem | Rating | Rationale |
| :--- | :---: | :--- |
| **Design Stack Overflow** | 2.0/10 | Too niche for LLD; often leans more into database schema design. |
| **Design CricInfo** | 3.0/10 | Focuses heavily on real-time data streaming and HLD rather than OOP. |
| **Design Traffic Signal Control** | 3.0/10 | A bit too hardware/state-machine specific. |
| **Design LinkedIn** | 3.5/10 | Extremely HLD-focused (graph databases, recommendation engines). |
| **Design Course Registration** | 3.5/10 | A very basic CRUD application that doesn't challenge design patterns enough. |
| **Design Coffee Vending Machine** | 4.0/10 | Redundant if you already know how to design a standard Vending Machine. |
| **Design Airline Management** | 4.5/10 | Overly complex domain logic makes this hard to finish in a 45-minute interview. |
| **Design an Online Auction** | 4.5/10 | Focuses more on concurrency and locking (HLD concepts) than pure class structures. |

### 🔹 Foundational but Less Frequent (5.0 - 6.5)
*These are good for practicing basic Object-Oriented Programming (OOP) but are either slowly phasing out of modern interviews or are usually asked as HLD questions.*

| Problem | Rating | Rationale |
| :--- | :---: | :--- |
| **Design ATM** | 5.0/10 | A classic State/Chain of Responsibility pattern problem, but slightly outdated. |
| **Design a Social Network** | 5.0/10 | Primarily an HLD question, though designing Post/Comment models is good practice. |
| **Design Hotel Management** | 5.5/10 | Standard entity modeling, but less common than booking a specific ticket/seat. |
| **[Design Music Streaming (Spotify)](./Projects/Spotify)** | 5.5/10 | Good for state management (Play/Pause), audio engine abstractions, and device adapter strategy. |
| **Design a Task Management System**| 6.0/10 | Good practice for the Command pattern, but lacks the complexity tier-1 companies want. |
| **Design Car Rental System** | 6.0/10 | A solid inventory management problem, but slightly easier than modern equivalents. |
| **Design Online Stock Brokerage** | 6.0/10 | Excellent for the Observer pattern, but financial domain logic can distract from pure design. |
| **Design a Digital Wallet Service** | 6.5/10 | Great for practicing concurrency and thread safety; core OOP design is relatively simple. |
| **[Design Dating App (Tinder)](./Projects/Tinder)** | 6.5/10 | Great for practicing recommendation strategy, user profile matching, and geographic geo-hashing principles. |
| **Design Restaurant Management** | 6.5/10 | A simplified version of Food Delivery; usually skipped in favor of Swiggy/Zomato. |

### 🔹 Highly Relevant & Common (7.0 - 8.5)
*You should expect these in modern machine coding rounds. They perfectly balance entity modeling, design patterns, and algorithmic thinking.*

| Problem | Rating | Rationale |
| :--- | :---: | :--- |
| **Design Pub Sub System** | 7.0/10 | The ultimate test for the Observer pattern and concurrent queue management. |
| **[Design Document Editor](./Projects/DocumentEditor)** | 7.5/10 | Excellent for demonstrating Decorator, Command, and Flyweight patterns alongside clean architecture refactoring. |
| **Design Logging Framework** | 7.5/10 | The standard way companies test your knowledge of Chain of Responsibility and Singleton. |
| **Design a Library Management** | 7.5/10 | The quintessential basic OOP interview question. Expect this in early-career interviews. |
| **Design Ride-Sharing (Uber)** | 7.5/10 | Tests location-based matching algorithms alongside driver/rider state machines. |
| **Design Online Shopping** | 7.5/10 | Massive scope, but tests core concepts like Cart, Checkout, and Payment interfaces. |
| **[Design Notification System](./Projects/NotificationSystem)** | 8.0/10 | Highly asked for evaluating Observer, Strategy, and Decorator patterns across multi-channel alerting engines. |
| **[Design Payment Gateway System](./Projects/PaymentGatewaySystem)** | 8.0/10 | Essential fintech interview problem testing strategy routing, transaction state machines, and third-party integrations. |
| **Design a Vending Machine** | 8.0/10 | The absolute gold standard for testing the **State Design Pattern**. |
| **Design a Concert Ticket System** | 8.0/10 | A great concurrency challenge (handling simultaneous bookings). |
| **Design LRU Cache** | 8.5/10 | Extremely frequent. Tests combining Data Structures (HashMap + Doubly Linked List) cleanly. |
| **[Design Tic Tac Toe Game](./Projects/TicTacToe)** | 8.5/10 | The most common entry-level LLD question. Tests extensibility (e.g., N x N grids). |

### 🔥 The Absolute Classics / "Must-Knows" (9.0 - 10.0)
*If you are preparing for LLD at Tier-1 tech companies, you must be able to code these flawlessly. They are asked constantly.*

| Problem | Rating | Rationale |
| :--- | :---: | :--- |
| **Design an Elevator System** | 9.0/10 | The ultimate test of state management, direction algorithms (e.g., SCAN), and concurrent requests. |
| **[Design Snake and Ladder Game](./Projects/SnakesAndLadders)** | 9.0/10 | A staple board game question testing entity encapsulation and cleanly decoupled game loops. |
| **Design Movie Ticket Booking** | 9.5/10 | Often framed as "BookMyShow." Tests locking mechanisms, seat mapping, payments, and concurrency. |
| **[Design Food Delivery (Zomato / Swiggy)](./Projects/ZomatoFoodDeliveryApp)** | 9.5/10 | Combines complex state machines (Order Status) with the Strategy pattern (routing, pricing). |
| **Design Parking Lot** | 10.0/10 | **The #1 most asked LLD question globally.** Tests hierarchy, extensibility, Factory, and Strategy patterns. |
| **[Design Chess Game](./Projects/Chess)** | 10.0/10 | The gold standard of OOP. Tests deep polymorphism, game state management, and validation logic. |
| **[Design Splitwise](./Projects/SplitwiseApp)** | 10.0/10 | A heavily requested FAANG problem. Tests user management, expense splitting algorithms, and graph simplification. |