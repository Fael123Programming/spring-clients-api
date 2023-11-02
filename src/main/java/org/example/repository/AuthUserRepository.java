package org.example.repository;


import org.example.domain.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    AuthUser findByUsername(String username);
}
