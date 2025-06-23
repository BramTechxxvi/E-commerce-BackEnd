package org.bram.services;

public interface UserServices {

    String getFullName();

    ChangeEmailResponse changeEmail(ChangeEmailRequest request);

    ChangePasswordResponse changePassword(ChangePasswordRequest request);

}
