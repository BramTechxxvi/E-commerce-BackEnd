package org.bram.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.bram.data.models.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class RegisterRequest {
    @Id
    private String id;
    @NotBlank(message="Enter your first name")
    private String firstName;
    @NotBlank(message = "Enter your last name")
    private String lastName;
    @NotBlank(message = "Enter your email")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+\\.[a-zA-Z]{2,}$", message = "Invalid email")
    private String email;
    @NotBlank
    @Pattern(regexp = "^0[7-9][0-1]\\d[- ]?\\d{3}[- ]?[0-9]{4}$", message = "Invalid phone")
    private String phone;
    @NotBlank(message = "Register as Customer or Seller")
    private String userRole;
    @NotBlank(message = "Enter your password")
    @Size(min = 8, max = 16, message = "Password must be between 8 to 16 characters")
    private String password;
    @NotBlank(message = "Enter street name")
    private String street;
    @NotBlank(message = "Enter your city")
    private String city;
    @NotBlank(message = "Enter your state")
    private String state;
    @NotBlank(message = "Enter house number")
    private String houseNumber;
    @NotBlank(message = "Enter your country")
    private String country;

}
