package com.atrdev.reactive.users.service;

import reactor.core.publisher.Mono;

public interface JwtService {
    String generateToken(String subject);
    Mono<Boolean> validateJwt(String token);
    String extractTokenSubject(String token);
}
