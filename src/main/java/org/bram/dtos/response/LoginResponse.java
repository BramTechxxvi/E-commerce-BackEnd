package org.bram.dtos.response;

import lombok.Data;

@Data
public class LoginResponse {

    private String message;
    private String token;
    private boolean success;
}
