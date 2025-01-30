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


## Introduction
This course covers how to create reactive, non-blocking applications using the **Spring Framework** and its **Spring WebFlux** module. 

![image](./SpringFrameworkandReactiveSpecification.png)

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


