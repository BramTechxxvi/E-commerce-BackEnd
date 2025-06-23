package org.bram.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="Users")
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @DBRef
    private Address address;
    private String password;
    private boolean isLoggedIn;

    public static interface ProductRepository {
    }
}
