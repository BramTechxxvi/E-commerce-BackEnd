package org.bram.data.models;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Document(collection="Admins")
public class Admin extends User{
}
