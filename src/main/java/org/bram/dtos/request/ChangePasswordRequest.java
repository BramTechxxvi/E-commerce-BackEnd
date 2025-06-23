package org.bram.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ChangePasswordRequest {

    @Id
    private String userId;
    @NotBlank(message = "Enter old password")
    @Size(min = 8, max = 16, message = "Password must be between 8 to 16 characters")
    private String oldPassword;
    @NotBlank(message = "Enter new password")
    @Size(min = 8, max = 16, message = "Password must ne between 8 to 16 characters")
    private String newPassword;

}
