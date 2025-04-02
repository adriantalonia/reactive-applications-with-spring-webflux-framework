package com.atrdev.reactive.users.presentation;

import com.atrdev.reactive.users.presentation.model.AuthenticationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @PostMapping("/login")
    public Mono<ResponseEntity<?>> login(@RequestBody @Validated Mono<AuthenticationRequest> authenticationRequestMono) {
        return Mono.just(ResponseEntity.ok().build());
    }
}
