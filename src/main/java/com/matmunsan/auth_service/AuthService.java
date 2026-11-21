package com.matmunsan.auth_service;

import com.matmunsan.auth_service.dto.RegisterRequest;
import com.matmunsan.auth_service.dto.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setPasswordHash(passwordEncoder.encode(request.username()));
        User saved = userRepository.save(user);

        return toResponse(saved);
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername());
    }
}
