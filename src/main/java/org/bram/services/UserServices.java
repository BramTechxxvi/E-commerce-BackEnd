package org.bram.services;

public interface UserServices {

    String getFullName();

    ChangeEmailResponse changeEmail(ChangeEmailRequest request);
//
//    updateEmail(newEmail): void
//
//    checkPassword(plainPwd): boolean
//
//    changePassword(oldPwd, newPwd): boolean
//
//    validate(): boolean (email format, phone number, etc.)
}
