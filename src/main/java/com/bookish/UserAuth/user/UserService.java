package com.bookish.UserAuth.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public AuthUser signUpUser(AuthUser authUser) {
        boolean existingUser = userRepository.findByEmail(authUser.getEmail()).isPresent();
        if (existingUser) throw new IllegalStateException("email already in use");

        String encodedPassword = bCryptPasswordEncoder.encode(authUser.getPassword());
        authUser.setPassword(encodedPassword);

        return userRepository.save(authUser);
    };
}
