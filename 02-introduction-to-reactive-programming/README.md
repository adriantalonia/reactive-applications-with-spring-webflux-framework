# Reactive Non-Blocking Applications with Spring WebFlux

## Table of Contents

- [Reactive Non-Blocking Applications with Spring WebFlux](#reactive-non-blocking-applications-with-spring-webflux)
  - [Table of Contents](#table-of-contents)
  - [Introduction](#introduction)
  - [What is Spring WebFlux?](#what-is-spring-webflux)
    - [Key Components](#key-components)
  - [Project Reactor and Reactive Data Types](#project-reactor-and-reactive-data-types)
  - [Reactive Streams Specification](#reactive-streams-specification)
    - [Relationship Between Components](#relationship-between-components)
  - [Summary](#summary)
  - [Next Steps](#next-steps)
- [Introduction to Reactive Programming](#introduction-to-reactive-programming)
  - [Overview](#overview)
  - [Key Concepts](#key-concepts)
    - [1. **Non-Blocking vs. Asynchronous Operations**](#1-non-blocking-vs-asynchronous-operations)
    - [2. **Core Principles of Reactive Programming**](#2-core-principles-of-reactive-programming)
      - [**Data Streams**](#data-streams)
      - [**Propagation of Change**](#propagation-of-change)
    - [3. **Advantages of Reactive Programming**](#3-advantages-of-reactive-programming)
  - [Functional Programming in Reactive Applications](#functional-programming-in-reactive-applications)
    - [**Imperative vs. Functional Programming**](#imperative-vs-functional-programming)
      - [**Imperative Approach (Traditional Spring MVC Example)**](#imperative-approach-traditional-spring-mvc-example)
      - [**Functional Approach (Reactive Programming Example)**](#functional-approach-reactive-programming-example)
    - [**Example: Converting to Functional Programming**](#example-converting-to-functional-programming)
  - [Conclusion](#conclusion)
- [Reactive Data Streams in Spring Applications](#reactive-data-streams-in-spring-applications)
  - [Key Concepts](#key-concepts-1)
    - [1. Data Streaming in Reactive Applications](#1-data-streaming-in-reactive-applications)
    - [2. Publisher and Subscriber](#2-publisher-and-subscriber)
    - [3. Controlling Data Flow](#3-controlling-data-flow)
    - [4. Backpressure](#4-backpressure)
  - [Example](#example)
  - [Summary](#summary-1)
  - [To-Do](#to-do)
- [Backpressure and Data Streams in Reactive Programming](#backpressure-and-data-streams-in-reactive-programming)
  - [Key Concepts](#key-concepts-2)
    - [What is Backpressure?](#what-is-backpressure)
    - [How Backpressure Works](#how-backpressure-works)
    - [Default Behavior](#default-behavior)
    - [Importance of Backpressure](#importance-of-backpressure)
  - [Examples](#examples)
    - [Example 1: Using the Request Method](#example-1-using-the-request-method)
    - [Example 2: Default Behavior](#example-2-default-behavior)
    - [Example 3: Handling Slow Subscribers](#example-3-handling-slow-subscribers)
  - [Summary](#summary-2)
    - [Key Takeaways](#key-takeaways)
- [Understanding Reactive and Non-Reactive Spring Applications](#understanding-reactive-and-non-reactive-spring-applications)
  - [Overview](#overview-1)
  - [Key Differences: Traditional Spring MVC vs. Reactive Spring Boot](#key-differences-traditional-spring-mvc-vs-reactive-spring-boot)
  - [Core Concepts in Reactive Programming](#core-concepts-in-reactive-programming)
  - [Examples](#examples-1)
    - [Example 1: Blocking vs. Non-blocking Database Call](#example-1-blocking-vs-non-blocking-database-call)
      - [Traditional Blocking Approach (Spring MVC with JDBC)](#traditional-blocking-approach-spring-mvc-with-jdbc)
      - [Reactive Non-blocking Approach (Spring WebFlux with R2DBC)](#reactive-non-blocking-approach-spring-webflux-with-r2dbc)
    - [Example 2: Reactive Web Client (Non-blocking HTTP Requests)](#example-2-reactive-web-client-non-blocking-http-requests)
      - [Traditional Blocking RestTemplate (Spring MVC)](#traditional-blocking-resttemplate-spring-mvc)
      - [Reactive WebClient (Spring WebFlux)](#reactive-webclient-spring-webflux)
  - [Conclusion](#conclusion-1)
- [Spring WebFlux Overview](#spring-webflux-overview)
  - [Introduction](#introduction-1)
  - [Key Differences Between Spring MVC and Spring WebFlux](#key-differences-between-spring-mvc-and-spring-webflux)
  - [Why Use Spring WebFlux?](#why-use-spring-webflux)
  - [How to Use Spring WebFlux](#how-to-use-spring-webflux)
    - [Adding WebFlux Dependency (Maven)](#adding-webflux-dependency-maven)
    - [Defining a Reactive REST Controller](#defining-a-reactive-rest-controller)
      - [Example: Traditional Spring MVC Controller (Blocking)](#example-traditional-spring-mvc-controller-blocking)
      - [Example: WebFlux Controller (Non-Blocking)](#example-webflux-controller-non-blocking)
    - [Functional Endpoints in WebFlux](#functional-endpoints-in-webflux)
      - [Example: Functional Endpoint](#example-functional-endpoint)
    - [Reactive Database Access](#reactive-database-access)
      - [Example: Reactive Repository with R2DBC](#example-reactive-repository-with-r2dbc)
  - [Summary](#summary-3)


## Introduction
This course covers how to create reactive, non-blocking applications using the **Spring Framework** and its **Spring WebFlux** module. 

![image](./resources/images/SpringFrameworkandReactiveSpecification.png)

## What is Spring WebFlux?
Spring Framework supports both **traditional non-reactive** and **reactive** applications. To enable reactive programming, Spring provides **Spring WebFlux**, a module designed for building **reactive applications**.

### Key Components
- **Spring WebFlux** is **not** a separate framework but a module within Spring Framework.
- It leverages **Project Reactor**, a powerful library for handling data asynchronously and in a **non-blocking** manner.
- Project Reactor allows handling multiple HTTP requests concurrently without waiting for tasks to complete sequentially.

## Project Reactor and Reactive Data Types
Spring WebFlux relies on **Project Reactor**, which introduces two key reactive data types:

- **Mono** → Returns at most **one** item.
- **Flux** → Returns **multiple** items.

These types make it easier to handle data reactively in Spring applications.

## Reactive Streams Specification
Reactive programming in Spring follows the **Reactive Streams Specification**, a standard for **non-blocking data processing**. It defines four core interfaces:

1. **Publisher** → Emits data asynchronously.
2. **Subscriber** → Consumes and processes data emitted by the Publisher.
3. **Subscription** → Manages the connection between Publisher and Subscriber.
4. **Processor** → Acts as both a Publisher and a Subscriber, allowing transformation of data.

### Relationship Between Components
- If a **Publisher** emits at most one item → use **Mono**.
- If a **Publisher** emits multiple items → use **Flux**.

## Summary
- **Reactive Streams Specification** defines a standard for non-blocking data handling.
- **Project Reactor** provides implementations for these standards.
- **Mono and Flux** are the key reactive types used in Spring WebFlux.
- **Spring WebFlux** enables the creation of fully reactive web applications using Spring Framework.

## Next Steps
In the following lessons, we will explore these concepts in more detail and build a **fully functional reactive application** using **Spring WebFlux**.

---


# Introduction to Reactive Programming

## Overview
Reactive programming enables the development of non-blocking applications that can efficiently handle both asynchronous and synchronous operations. It is particularly useful for applications that need to support a large number of concurrent users.

## Key Concepts

### 1. **Non-Blocking vs. Asynchronous Operations**
- Reactive applications are non-blocking but do not necessarily need to be asynchronous.
- Synchronous operations can still be part of a reactive system, particularly when working with in-memory data or business logic that does not involve I/O operations.

### 2. **Core Principles of Reactive Programming**
#### **Data Streams**
- A data stream is a sequence of data elements made available over time.
- Similar to a continuous flow of data, like water in a river.
- Reactive programming processes each element as it arrives instead of waiting for the entire dataset.

#### **Propagation of Change**
- When data changes, the change automatically propagates through the system.
- Similar to spreadsheet formulas, where updating one cell updates all dependent cells.

### 3. **Advantages of Reactive Programming**
- Efficiently handles large-scale concurrent operations.
- Uses functional programming concepts for cleaner, more maintainable code.
- Minimizes the need for explicit flow control structures like `if-else` and `try-catch`.

## Functional Programming in Reactive Applications
Reactive programming leverages a functional programming style rather than an imperative approach. This involves:
- Using **reactive streams** to handle data flow.
- Implementing **Lambda functions** for concise, readable code.
- Utilizing **operators** like `map` and `filter` for data processing.

### **Imperative vs. Functional Programming**
#### **Imperative Approach (Traditional Spring MVC Example)**
- Explicitly stores values in variables.
- Uses `if-else` conditions to control program flow.
- Handles errors with `try-catch` blocks.

#### **Functional Approach (Reactive Programming Example)**
- Uses **function composition** instead of step-by-step instructions.
- Chains together operators in a **pipeline** to process data.
- Replaces `if-else` with **operators** like `switchIfEmpty`.

### **Example: Converting to Functional Programming**
```typescript
getUserById(id: string) {
  return userRepository.findById(id)
    .map(user -> transform(user))
    .switchIfEmpty(Mono.just(defaultUser));
}
```
- No explicit variables.
- No `if-else` or `try-catch` blocks.
- Uses `map` to transform data and `switchIfEmpty` to handle missing results.

## Conclusion
Reactive programming allows developers to build efficient, scalable applications using functional programming techniques. By leveraging reactive streams and operators, developers can create **non-blocking** and **highly concurrent** systems with cleaner and more maintainable code.

In upcoming lessons, you will explore various **reactive operators** and learn how to apply them effectively in real-world applications.

---

# Reactive Data Streams in Spring Applications

This lesson explores the flow of data in reactive programming, emphasizing how data streams are processed between components in a reactive Spring application. Below are key concepts, examples, and takeaways to help understand reactive data streams and their implementation using the Reactive Streams specification.

![image](./resources/images/datastream.png)

## Key Concepts

### 1. Data Streaming in Reactive Applications
Reactive applications process items as they arrive rather than waiting for all items to be ready. For example:

- A database retrieves 500 records.
- Instead of sending all records as one large collection, they are streamed one by one to the subscriber.
- The subscriber processes each item as it arrives, creating a continuous and asynchronous flow.

Benefits:
- **Memory Efficiency:** No need to hold all records in memory.
- **Non-blocking:** The application can handle other tasks while processing items.
- **Streaming:** Results can be sent to the client even before all records are retrieved.

### 2. Publisher and Subscriber
These terms come from the Reactive Streams specification:
- **Publisher:** Emits a sequence of items.
- **Subscriber:** Consumes items from the publisher according to its demand.

Flow of data:
- The subscriber calls the `subscribe()` method on the publisher to initiate the subscription process.
- The publisher sends a `subscription` object to the subscriber.
- The subscriber uses this object to request items and control the rate of data flow.
- The publisher calls the `onNext()` method to deliver each item one by one.
- Once all items are sent, the publisher calls `onComplete()`. If an error occurs, `onError()` is called instead.

![image](./resources/images/datastream2.png)

### 3. Controlling Data Flow
Subscribers can control the flow of data by specifying how many items to request at a time using the `request()` method. This mechanism helps manage data processing rates and prevents overload.

### 4. Backpressure
If the publisher produces data faster than the subscriber can consume it, backpressure comes into play to manage the flow of data efficiently.

## Example
A simple example of data streaming between components:

1. **Data Access Object (DAO):** Acts as a publisher and retrieves records from the database.
2. **Service Layer:** Acts as a subscriber to the DAO and a publisher to the controller.
3. **Controller:** Acts as the final subscriber that sends processed data to the client.

Example code snippet:
```java
repository.findAllUsers()  // Publisher
          .filter(user -> user.isActive())
          .map(user -> transformUser(user))
          .doOnError(error -> log.error("Error fetching users", error))
          .subscribe(user -> sendToClient(user));  // Subscriber
```

![image](./resources/images/datastream3.png)

## Summary
In reactive programming:
- Data is streamed between components as items arrive.
- Publishers emit items one at a time, and subscribers process them immediately.
- The subscription object allows for controlling data flow and handling errors.
- Backpressure helps manage cases where data production exceeds consumption capacity.

This reactive flow ensures efficient, non-blocking, and scalable data handling in Spring applications. Subsequent lessons will include practical examples with logging enabled to visualize the streaming process.

## To-Do
- **Next Lesson:** Explore the concept of Backpressure in more detail.
- **Hands-on Example:** Build a REST API with Spring Framework to demonstrate reactive data streams in action.

--- 

# Backpressure and Data Streams in Reactive Programming

This lesson focuses on understanding backpressure in reactive programming and how subscribers can manage data streams effectively.

## Key Concepts

### What is Backpressure?
Backpressure is a mechanism that allows subscribers to control how much data they receive from publishers. This prevents subscribers from being overwhelmed by a fast publisher, ensuring smooth operation even under heavy data loads.

### How Backpressure Works
- The **request method** is used to specify the number of items the subscriber is ready to process.
- When invoked, the request method tells the publisher how many items to send.
- The publisher then sends only the number of items specified.

### Default Behavior
- By default, the request method requests all items at once, which can be problematic if the subscriber is slow.
- Unprocessed items are held in memory, potentially slowing down the application if there is insufficient memory.

### Importance of Backpressure
- Critical in scenarios where publishers emit large numbers of items.
- Helps avoid memory overload and performance degradation.

## Examples

### Example 1: Using the Request Method
```java
// Example of controlling data flow
subscription.request(10); // Request 10 items from the publisher
```
This tells the publisher to send exactly 10 items to the subscriber.

### Example 2: Default Behavior
```java
// Default request behavior
subscription.request(Long.MAX_VALUE); // Requests all items at once
```
This default behavior can lead to issues if the subscriber cannot process all items quickly.

### Example 3: Handling Slow Subscribers
```java
// Managing slow subscriber
subscription.request(5); // Subscriber processes 5 items at a time
```
This approach prevents the subscriber from being overwhelmed.

## Summary
Backpressure is essential for managing data streams in reactive programming. It enables subscribers to control the flow of data, ensuring efficient processing without overwhelming the system. In the next lessons, more advanced topics related to backpressure will be explored.

### Key Takeaways
- Use the request method to manage data flow effectively.
- Default behavior may lead to memory issues if subscribers are slow.
- Managing backpressure ensures smoother and more efficient application performance.

---
# Understanding Reactive and Non-Reactive Spring Applications

## Overview
In traditional Spring MVC applications, communication between components is mostly **blocking**. The execution follows an **imperative programming** model, which includes:
- Sequential execution of code
- Blocking I/O operations
- Synchronous step-by-step logic

![image](./resources/images/traditional-bloking-spring-mvc-rest-application.png)

However, **Reactive Spring Boot applications** use a completely different approach where communication is **non-blocking** from the start. This leads to **higher scalability** and **better resource utilization**.

## Key Differences: Traditional Spring MVC vs. Reactive Spring Boot

| Feature                 | Traditional Spring MVC         | Reactive Spring Boot |
|-------------------------|--------------------------------|-----------------------|
| **Execution Model**     | Imperative, Blocking          | Asynchronous, Non-blocking |
| **Embedded Server**     | Apache Tomcat (Blocking)      | Netty (Non-blocking) |
| **Database Drivers**    | JDBC, JPA (Blocking)          | R2DBC, Reactive Mongo, Cassandra, etc. (Non-blocking) |
| **Programming Style**   | Step-by-step synchronous logic | Event-driven, Reactive Streams |
| **Web Framework**       | Spring MVC (Servlet-based)    | Spring WebFlux |
| **Security Framework**  | Spring Security               | Spring Security Reactive |
| **Supported Web Servers** | Tomcat, Jetty, Undertow (Blocking) | Netty (default), Jetty (Reactive), Undertow (Reactive) |

![image](./resources/images/traditional-vs-reactive.png)


## Core Concepts in Reactive Programming
1. **Asynchronous Data Streams**: Data is processed as it arrives instead of waiting for all data to be available.
2. **Non-blocking I/O Operations**: Components do not wait for responses and can continue processing other tasks.
3. **Event-driven Architecture**: Actions are triggered based on events rather than sequential steps.

![image](./resources/images/reactive-application-overview.png)

## Examples
### Example 1: Blocking vs. Non-blocking Database Call
#### Traditional Blocking Approach (Spring MVC with JDBC)
```java
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll(); // Blocking call
    }
}
```

#### Reactive Non-blocking Approach (Spring WebFlux with R2DBC)
```java
@RestController
public class UserController {
    @Autowired
    private ReactiveUserRepository userRepository;

    @GetMapping("/users")
    public Flux<User> getUsers() {
        return userRepository.findAll(); // Non-blocking stream of users
    }
}
```

### Example 2: Reactive Web Client (Non-blocking HTTP Requests)
#### Traditional Blocking RestTemplate (Spring MVC)
```java
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response = restTemplate.getForEntity("https://api.example.com/data", String.class);
String body = response.getBody(); // Blocking call
```

#### Reactive WebClient (Spring WebFlux)
```java
WebClient webClient = WebClient.create("https://api.example.com");
Mono<String> response = webClient.get().uri("/data").retrieve().bodyToMono(String.class);
response.subscribe(System.out::println); // Non-blocking call
```

## Conclusion
Reactive Spring Boot applications provide **better performance and scalability** by eliminating blocking operations. However, they require a different mindset and coding style based on **Reactive Streams**. Choosing between traditional Spring MVC and Reactive Spring depends on your application’s requirements. If high throughput and responsiveness are needed, **Spring WebFlux** is the best choice.

---

# Spring WebFlux Overview

## Introduction
Spring WebFlux is a module within the Spring Framework that enables the development of reactive web applications and APIs. Unlike traditional Spring MVC applications, WebFlux is designed for non-blocking, asynchronous execution, making it ideal for handling a large number of concurrent requests with minimal resources.

## Key Differences Between Spring MVC and Spring WebFlux

| Feature            | Spring MVC (Blocking) | Spring WebFlux (Non-Blocking) |
|-------------------|----------------------|-------------------------------|
| Execution Model  | Imperative (Blocking) | Reactive (Non-Blocking) |
| Default Web Server | Apache Tomcat | Netty (Non-Blocking) |
| Database Access  | JDBC, JPA (Blocking) | R2DBC, MongoDB, Cassandra (Reactive) |
| Programming Model | Annotation-based MVC | Annotation-based & Functional Endpoints |
| Security         | Spring Security | Spring Security Reactive |
| Concurrency Handling | Thread-per-request | Event-driven, Handles More Requests with Fewer Threads |

## Why Use Spring WebFlux?
- **High Performance:** Handles many concurrent requests efficiently.
- **Better Resource Utilization:** Uses fewer threads and non-blocking I/O operations.
- **Streaming Data Processing:** Handles data streams asynchronously.
- **Supports Both Imperative and Reactive Styles:** Developers can use annotations (like in Spring MVC) or functional endpoints.

## How to Use Spring WebFlux
### Adding WebFlux Dependency (Maven)
To enable WebFlux in a Spring Boot application, replace `spring-boot-starter-web` with `spring-boot-starter-webflux` in `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

### Defining a Reactive REST Controller
Spring WebFlux supports annotation-based controllers similar to Spring MVC but returns `Flux<T>` or `Mono<T>` instead of `List<T>`.

#### Example: Traditional Spring MVC Controller (Blocking)
```java
@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUsers();
    }
}
```

#### Example: WebFlux Controller (Non-Blocking)
```java
@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping
    public Flux<User> getAllUsers() {
        return userService.getUsers();
    }
}
```
Here, `Flux<User>` represents a reactive stream of multiple user objects.

### Functional Endpoints in WebFlux
WebFlux allows defining routes functionally instead of using annotation-based controllers.

#### Example: Functional Endpoint
```java
@Configuration
public class UserRouter {
    @Bean
    public RouterFunction<ServerResponse> route(UserHandler userHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/users"), userHandler::getAllUsers);
    }
}
```

### Reactive Database Access
Spring WebFlux integrates with non-blocking, reactive databases such as MongoDB, Cassandra, and R2DBC for relational databases.

#### Example: Reactive Repository with R2DBC
```java
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {}
```

## Summary
Spring WebFlux provides a powerful way to build scalable, non-blocking applications. It:
- Uses **Netty** instead of Tomcat (by default).
- Handles **high concurrency** without increasing system resource consumption.
- Supports both **annotation-based controllers** and **functional endpoints**.
- Works well with **reactive database drivers** and **event-driven architecture**.
- Uses `Flux<T>` and `Mono<T>` instead of `List<T>` for data handling.

For applications requiring high scalability and responsiveness, Spring WebFlux is an excellent choice over traditional blocking Spring MVC.

---
_This document provides a concise yet detailed overview of Spring WebFlux, its advantages, and how to implement it in your applications._

