package com.atrdev.reactive.users.service;

public interface JwtService {
    String generateToken(String subject);
}
