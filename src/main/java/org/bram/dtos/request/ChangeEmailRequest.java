package org.bram.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ChangeEmailRequest {

    @Id
    private String userId;
    @NotBlank(message = "Enter your old email")
    private String oldEmail;
    @NotBlank(message = "Enter your new email")
    private String newEmail;
}
