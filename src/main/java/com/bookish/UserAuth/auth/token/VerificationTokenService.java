package com.bookish.UserAuth.auth.token;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;

    public void saveToken(VerificationToken verificationToken) {
        verificationTokenRepository.save(verificationToken);
    }

    public Optional<VerificationToken> getToken(String verificationToken) {
        return verificationTokenRepository.findByToken(verificationToken);
    }

    public void setConfirmedAt(String token) {
        verificationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
