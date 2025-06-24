package org.bram.dtos.request;

import lombok.Data;
import org.bram.data.models.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document
@Data
public class RegisterRequest {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Address address;
    private String userRole;
    private String password;

}
