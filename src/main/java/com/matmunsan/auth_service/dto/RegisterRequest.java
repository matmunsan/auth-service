package com.matmunsan.auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(@NotBlank String username,
                              @NotBlank @Size(min = 8,max = 72, message = "Password must have at least 8 characters") String password) {
}
