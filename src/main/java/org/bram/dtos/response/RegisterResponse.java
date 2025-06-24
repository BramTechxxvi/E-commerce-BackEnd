package org.bram.dtos.response;

import lombok.Data;

@Data
public class RegisterResponse {
    private String message;
    private boolean success;
    private String fullName;
}
