package com.atrdev.reactive.users.presentation;

import com.atrdev.reactive.users.presentation.model.AuthenticationRequest;
import com.atrdev.reactive.users.service.AuthenticationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<Object>> login(@RequestBody @Validated Mono<AuthenticationRequest> authenticationRequestMono) {
        return authenticationRequestMono
                .flatMap(authenticationRequest ->
                        authenticationService
                                .authenticate(authenticationRequest.getEmail(),
                                        authenticationRequest.getPassword()))
                .map(authenticationResultMap -> ResponseEntity.ok()
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + authenticationResultMap.get("token"))
                        .header("UserId", authenticationResultMap.get("userId"))
                        .build());
                //.onErrorReturn(BadCredentialsException.class, ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"))
                //.onErrorReturn(Exception.class, ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
