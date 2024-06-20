package com.bookish.UserAuth.auth;

import com.bookish.UserAuth.user.AuthUser;
import com.bookish.UserAuth.user.UserRole;
import com.bookish.UserAuth.user.UserService;
import com.bookish.UserAuth.utils.EmailValidator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private final EmailValidator emailValidator;
    private final UserService userService;

    public AuthUser register(AuthRequest authRequest) {
        boolean isValidEmail = emailValidator.isValidEmail(authRequest.getEmail());
        if(!isValidEmail) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid email");

        return userService.signUpUser(new AuthUser(
                authRequest.getFirstName(),
                authRequest.getLastName(),
                authRequest.getEmail(),
                authRequest.getPassword(),
                UserRole.USER
        ));
    }
}
