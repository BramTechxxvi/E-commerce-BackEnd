package org.bram.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Enter your email")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+\\.[a-zA-Z]{2,}$", message = "Invalid email")
    private String email;
    @NotBlank(message = "Enter your password")
    @Size(min= 8, max=16, message = "Password must be between 8 to 16 characters")
    private String password;
    @NotBlank(message = "Enter role")
    private String role;
}
