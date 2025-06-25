package org.bram.data.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Admins")
public class Admin extends User{
}
