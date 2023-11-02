package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.repository.AuthUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserService implements UserDetailsService {
    private final AuthUserRepository authUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return Optional.ofNullable(authUserRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username '%s' not found", username)));
    }
}
