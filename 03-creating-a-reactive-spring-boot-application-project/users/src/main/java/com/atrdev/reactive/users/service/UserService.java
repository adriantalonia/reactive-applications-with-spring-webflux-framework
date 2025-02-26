package com.atrdev.reactive.users.service;

import com.atrdev.reactive.users.presentation.CreateUserRequest;
import com.atrdev.reactive.users.presentation.UserRest;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {
    Mono<UserRest> createUser(Mono<CreateUserRequest> createUserRequestMono);
    Mono<UserRest> getUserById(UUID userId);
}
