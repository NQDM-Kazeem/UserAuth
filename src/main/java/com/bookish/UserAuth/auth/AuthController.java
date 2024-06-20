package com.bookish.UserAuth.auth;

import com.bookish.UserAuth.user.AuthUser;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> signUp(@RequestBody AuthRequest authRequest, UriComponentsBuilder ucb) {
        AuthUser newUser = authService.register(authRequest);
        URI newUserLocation = ucb.path("users/{id}").buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity.created(newUserLocation).build();
    }
}
