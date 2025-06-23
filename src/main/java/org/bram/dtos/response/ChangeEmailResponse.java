package org.bram.dtos.response;

import lombok.Data;

@Data
public class ChangeEmailResponse {

    private String message;
    private boolean success;
}
