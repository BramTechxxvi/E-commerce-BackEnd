package org.bram.dtos.response;

import lombok.Data;

@Data
@ALL
public class RegisterResponse {
    private String message;
    private boolean success;
}
