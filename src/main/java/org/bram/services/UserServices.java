package org.bram.services;

import org.bram.dtos.request.*;
import org.bram.dtos.response.*;

public interface UserServices {

    ChangeEmailResponse changeEmail(ChangeEmailRequest request);

    ChangePasswordResponse changePassword(ChangePasswordRequest request);

}
