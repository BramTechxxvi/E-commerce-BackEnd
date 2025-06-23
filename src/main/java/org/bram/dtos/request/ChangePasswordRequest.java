package org.bram.dtos.request;

import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.annotation.Id;

@Data
public class ChangePasswordRequest {

    @Id
    private String userId;
    private String oldPassword;
    private String newPassword;

}
