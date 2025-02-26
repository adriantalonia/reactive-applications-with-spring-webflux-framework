package com.atrdev.reactive.users.presentation;

import com.atrdev.reactive.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@RestController // Marks this class as a REST controller, capable of handling HTTP requests.
@RequestMapping("/users") // Base URL mapping for all endpoints in this controller.
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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

        return userService.createUser(createUserRequest)
                .map(userRest -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .location(URI.create("/users/" + userRest.getId()))
                        .body(userRest));


        // Maps the incoming request to a new UserRest object with a generated UUID.
        /*return createUserRequest.map(request ->
                        new UserRest(UUID.randomUUID(),
                                request.getFirstName(),
                                request.getLastName(),
                                request.getEmail()))*/
        // Wraps the created user in a ResponseEntity with HTTP status 201 (CREATED)
        // and sets the Location header to the URL of the newly created user.
                /*.map(userRest -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .location(URI.create("/users/" + userRest.getId()))
                        .body(userRest));*/
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param userId The UUID of the user to retrieve.
     * @return A Mono containing the UserRest object representing the requested user.
     */
    @GetMapping("/{userId}")
    public Mono<ResponseEntity<UserRest>> getUser(@PathVariable("userId") UUID userId) {
        return userService.getUserById(userId)
                .map(userRest -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(userRest))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
        // Returns a mock user with the provided userId and placeholder data.
        //return Mono.just(new UserRest(userId, "First Name", "Last Name", "Email"));
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
        // Returns a Flux of mock users with placeholder data.
        return Flux.just(
                new UserRest(UUID.randomUUID(), "First Name", "Last Name", "Email"),
                new UserRest(UUID.randomUUID(), "First Name", "Last Name", "Email"),
                new UserRest(UUID.randomUUID(), "First Name", "Last Name", "Email"));
    }
}