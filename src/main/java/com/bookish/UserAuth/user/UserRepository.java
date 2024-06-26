package com.bookish.UserAuth.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AuthUser u " + "SET u.isActive = TRUE WHERE u.email = ?1")
    void activateAuthUser(String email);
}
