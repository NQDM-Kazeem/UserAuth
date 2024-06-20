package com.bookish.UserAuth.auth;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AuthRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
