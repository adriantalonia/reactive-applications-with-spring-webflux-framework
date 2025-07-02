package com.atrdev.reactive.users.presentation;

import com.atrdev.reactive.users.presentation.model.CreateUserRequest;
import com.atrdev.reactive.users.presentation.model.UserRest;
import com.atrdev.reactive.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.UUID;

@RestController // Marks this class as a REST controller, capable of handling HTTP requests.
@RequestMapping("/users") // Base URL mapping for all endpoints in this controller.
public class UserController {

    // Service layer dependency for user operations
    private final UserService userService;
    // Configuration for server codecs (used for request/response encoding/decoding)
    private final ServerCodecConfigurer serverCodecConfigurer;

    // Constructor for dependency injection
    public UserController(UserService userService, ServerCodecConfigurer serverCodecConfigurer) {
        this.userService = userService;
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    /**
     * Handles the creation of a new user.
     *
     * @param createUserRequest A Mono containing the user creation request data.
     * @return A Mono containing a ResponseEntity with the created user and HTTP status 201 (CREATED).
     */
    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED) // Uncomment to set the default response status to 201 CREATED.
    public Mono<ResponseEntity<UserRest>> createUser(@RequestBody @Validated Mono<CreateUserRequest> createUserRequest) {
        // Delegates user creation to the service layer and maps the response
        return userService.createUser(createUserRequest)
                .map(userRest -> ResponseEntity
                        .status(HttpStatus.CREATED) // Sets HTTP status to 201 (Created)
                        .location(URI.create("/users/" + userRest.getId())) // Sets Location header
                        .body(userRest)); // Includes the created user in the response body
    }

    /**
     * Retrieves a user by their unique ID.
     * Includes security check to ensure users can only access their own data unless they're admins.
     *
     * @param userId The UUID of the user to retrieve.
     * @return A Mono containing the UserRest object representing the requested user,
     *         or 404 NOT FOUND if the user doesn't exist.
     */
    @GetMapping("/{userId}")
    // Security annotations (commented out but available for reference):
    //@PreAuthorize("authentication.principal.equals(#userId.toString() or hasRole('ROLE_ADMIN'))")
    //@PreAuthorize("authentication.principal.equals(#userId.toString())")
    @PostAuthorize("returnObject.body != null and (returnObject.body.id.toString().equals(authentication.principal))")
    public Mono<ResponseEntity<UserRest>> getUser(@PathVariable("userId") UUID userId,
                                                  @RequestParam(name = "include", required = false) String include) {
        // Delegates user retrieval to the service layer
        return userService.getUserById(userId, include)
                .map(userRest -> ResponseEntity
                        .status(HttpStatus.OK) // Sets HTTP status to 200 (OK)
                        .body(userRest))
                // Returns 404 if user not found
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    /**
     * Retrieves a list of users with pagination support.
     *
     * @param offset The starting index for pagination (default is 0).
     * @param limit  The maximum number of users to return (default is 50).
     * @return A Flux containing a list of UserRest objects.
     */
    @GetMapping
    public Flux<UserRest> getUsers(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                   @RequestParam(value = "limit", defaultValue = "50") int limit) {
        // Delegates user listing to the service layer with pagination parameters
        return userService.findAll(offset, limit);
    }

    /**
     * Provides a Server-Sent Events (SSE) stream of user events.
     * This endpoint produces a continuous stream of events in text/event-stream format.
     *
     * @return A Flux emitting events every second with a sequence number.
     */
    @GetMapping(value = "/streamDemo", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamDemo() {
        // Creates an infinite stream emitting an event every second
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "Event " + sequence); // Simple event message with sequence number
    }


    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserRest> streamUsers() {
        return userService.streamUsers();
    }

}

/*
{

    private final UserService userService;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public UserController(UserService userService, ServerCodecConfigurer serverCodecConfigurer) {
        this.userService = userService;
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    */
/**
     * Handles the creation of a new user.
     *
     * @param createUserRequest A Mono containing the user creation request data.
     * @return A Mono containing a ResponseEntity with the created user and HTTP status 201 (CREATED).
     *//*

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED) // Uncomment to set the default response status to 201 CREATED.
    public Mono<ResponseEntity<UserRest>> createUser(@RequestBody @Validated Mono<CreateUserRequest> createUserRequest) {

        return userService.createUser(createUserRequest)
                .map(userRest -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .location(URI.create("/users/" + userRest.getId()))
                        .body(userRest));


        // Maps the incoming request to a new UserRest object with a generated UUID.
        */
/*return createUserRequest.map(request ->
                        new UserRest(UUID.randomUUID(),
                                request.getFirstName(),
                                request.getLastName(),
                                request.getEmail()))*//*

        // Wraps the created user in a ResponseEntity with HTTP status 201 (CREATED)
        // and sets the Location header to the URL of the newly created user.
                */
/*.map(userRest -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .location(URI.create("/users/" + userRest.getId()))
                        .body(userRest));*//*

    }

    */
/**
     * Retrieves a user by their unique ID.
     *
     * @param userId The UUID of the user to retrieve.
     * @return A Mono containing the UserRest object representing the requested user.
     *//*


    @GetMapping("/{userId}")
    //@PreAuthorize("authentication.principal.equals(#userId.toString() or hasRole('ROLE_ADMIN'))")
    //@PreAuthorize("authentication.principal.equals(#userId.toString())")
    @PostAuthorize("returnObject.body != null and (returnObject.body.id.toString().equals(authentication.principal))")
    public Mono<ResponseEntity<UserRest>> getUser(@PathVariable("userId") UUID userId) {
        return userService.getUserById(userId)
                .map(userRest -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(userRest))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
        // Returns a mock user with the provided userId and placeholder data.
        //return Mono.just(new UserRest(userId, "First Name", "Last Name", "Email"));
    }

    */
/**
     * Retrieves a list of users with pagination support.
     *
     * @param offset The starting index for pagination (default is 0).
     * @param limit  The maximum number of users to return (default is 50).
     * @return A Flux containing a list of UserRest objects.
     *//*

    @GetMapping
    public Flux<UserRest> getUsers(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                   @RequestParam(value = "limit", defaultValue = "50") int limit) {

        return userService.findAll(offset, limit);

        // Returns a Flux of mock users with placeholder data.
        */
/*return Flux.just(
                new UserRest(UUID.randomUUID(), "First Name", "Last Name", "Email"),
                new UserRest(UUID.randomUUID(), "First Name", "Last Name", "Email"),
                new UserRest(UUID.randomUUID(), "First Name", "Last Name", "Email"));*//*

    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamUsers() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "Event " + sequence);
    }
}*/
