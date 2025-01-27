# Introduction to Traditional Spring MVC Applications

Before we begin learning in detail about reactive Spring Boot applications, let's quickly review what a traditional Spring MVC application is.

## Table of Contents

- [Introduction to Traditional Spring MVC Applications](#introduction-to-traditional-spring-mvc-applications)
  - [Table of Contents](#table-of-contents)
  - [Traditional Spring MVC Web Application](#traditional-spring-mvc-web-application)
    - [Key Components](#key-components)
    - [Dependencies](#dependencies)
  - [How Traditional Spring MVC REST Applications Work](#how-traditional-spring-mvc-rest-applications-work)
    - [Code Organization](#code-organization)
    - [Communication](#communication)
    - [Blocking Web Server](#blocking-web-server)
    - [Limitations of Traditional Spring MVC Applications](#limitations-of-traditional-spring-mvc-applications)
      - [Thread-per-Request Architecture](#thread-per-request-architecture)
      - [Impact on Performance](#impact-on-performance)
      - [Conclusion](#conclusion)
  - [Solutions to the Thread-Per-Request Problem](#solutions-to-the-thread-per-request-problem)
    - [Increasing Thread Pool Size](#increasing-thread-pool-size)
    - [Vertical Scaling](#vertical-scaling)
    - [Horizontal Scaling](#horizontal-scaling)
    - [Virtual Threads](#virtual-threads)
    - [Reactive Programming](#reactive-programming)

## Traditional Spring MVC Web Application

Traditional Spring Boot applications are built with the Spring MVC framework, also known as Spring Web MVC.

### Key Components

- **Model, View, and Controller**: In a traditional Spring MVC application, we create a model, view, and controller. In the controller class, we use annotations like `@Controller` and `@RequestMapping` to display a web page to the user.
- **RESTful Web Services**: You can also create a Spring Boot application as a RESTful web service using Spring MVC. This approach is often called Spring REST or Spring MVC REST. It uses the `@RestController` annotation to return JSON or XML responses to the client.

### Dependencies

To create a traditional Spring web application using Spring Boot, add the `spring-boot-starter-web` dependency. If you use Gradle, add the same dependency using Gradle file format syntax.

## How Traditional Spring MVC REST Applications Work

Let's assume we have a client (e.g., a mobile application or JavaScript application) and a RESTful Spring Boot application called Forest Microservice.

### Code Organization

1. **Presentation Layer**: Contains the `@RestController` class and methods that handle HTTP requests and return HTTP responses.
2. **Service Layer**: Contains the main business logic of the application.
3. **Data Layer**: Contains code that reads and writes data to a database.

### Communication

In traditional Spring web applications, communication between these three layers is synchronous and blocking:

- The client application sends an HTTP request.
- The request is handled by a method in the `@RestController` class.
- The `@RestController` passes the information to a service class and waits for the business logic to complete.
- If the business logic needs to read or write to a database, the service layer invokes the data layer and waits for a response.
- The data layer executes SQL queries and waits for a response from the database.
- If the service layer needs to fetch information from a remote service, it sends an HTTP request and waits for a response.

The `@RestController` can only respond back to the client application after all components in the service layer have finished their tasks.

### Blocking Web Server

Traditional Spring MVC web applications work with a blocking web server, such as Apache Tomcat, which is usually embedded in the application archive.

---

### Limitations of Traditional Spring MVC Applications

Traditional Spring MVC web applications are considered blocking because they are deployed on a web server that uses a thread-per-request architecture. This means that for every incoming HTTP request, the server (e.g., Apache Tomcat) allocates a separate thread to handle the request.

#### Thread-per-Request Architecture

- **Thread Allocation**: When a client application (e.g., a mobile app) sends an HTTP request to a Spring MVC application, the web server assigns a dedicated thread to process the request.
- **Blocking Operations**: The thread remains occupied until the Spring MVC application completes the requested operation. This includes waiting for responses from other web services or database operations.
- **Resource Limitation**: The number of concurrent requests the application can handle is limited by the number of available threads. For example, Apache Tomcat typically has a default maximum of 200 threads, which can be configured.

#### Impact on Performance

- **Synchronous Processing**: Each thread handles requests synchronously, meaning it waits for each operation to complete before moving on to the next task.
- **Thread Pool Management**: Tomcat manages a thread pool, starting with a minimum number of threads (e.g., 25) and creating more as needed, up to the configured maximum.
- **Scalability Issues**: When the maximum number of threads is reached, additional incoming requests must wait for threads to become available, potentially leading to client-side errors and degraded performance.

#### Conclusion

The thread-per-request model can lead to scalability issues, as the server's ability to handle concurrent requests is limited by the number of threads it can manage. This architecture is suitable for applications with moderate traffic but may struggle under high load conditions. Solutions to this problem include adopting non-blocking, reactive programming models that can handle more concurrent requests with fewer resources.

---

## Solutions to the Thread-Per-Request Problem

In this video lesson, we explore various solutions to address the limitations of the thread-per-request model in traditional Spring MVC applications.

### Increasing Thread Pool Size

One straightforward solution is to increase the number of threads in the thread pool. This can help if the server has sufficient resources to handle more threads. However, this approach is limited by the server's hardware capabilities.

### Vertical Scaling

Vertical scaling involves upgrading the server with more memory and CPU resources. This allows the server to handle more concurrent requests by enhancing its computational power. As the application's demand grows, the server can be upgraded incrementally.

### Horizontal Scaling

Horizontal scaling adds more servers to distribute the load. Instead of upgrading a single server, multiple servers work together to handle a larger number of concurrent requests. This approach improves scalability and fault tolerance.

### Virtual Threads

Java 21 introduces virtual threads, which are lighter than traditional threads and not directly tied to operating system threads. Many virtual threads can share a small pool of platform threads, improving performance and allowing the application to handle more concurrent requests efficiently.

### Reactive Programming

Reactive programming with non-blocking I/O is another solution. By building non-blocking, asynchronous applications, reactive programming can handle a massive number of concurrent requests. This approach is ideal for applications requiring high scalability and responsiveness.

These solutions provide various ways to overcome the limitations of the thread-per-request model, enhancing the performance and scalability of traditional Spring MVC applications.
