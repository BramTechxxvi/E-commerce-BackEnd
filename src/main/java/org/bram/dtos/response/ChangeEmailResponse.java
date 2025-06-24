package org.bram.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeEmailResponse {

    private String message;
    private boolean success;
}
