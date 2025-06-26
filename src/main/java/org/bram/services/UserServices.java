package org.bram.services;

import org.bram.dtos.request.*;
import org.bram.dtos.response.*;

public interface UserServices {

    ApiResponse changeEmail(ChangeEmailRequest request);

    ApiResponse changePassword(ChangePasswordRequest request);

}
