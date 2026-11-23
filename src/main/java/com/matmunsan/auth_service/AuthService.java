package com.matmunsan.auth_service;

import com.matmunsan.auth_service.dto.RegisterRequest;
import com.matmunsan.auth_service.dto.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse register(RegisterRequest request) {
        String username = request.username().toLowerCase(Locale.ROOT);

        if (userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);

        String passwordHash= passwordEncoder.encode(request.password());

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(passwordHash);
        User saved = userRepository.save(user);

        return toResponse(saved);
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername());
    }
}
