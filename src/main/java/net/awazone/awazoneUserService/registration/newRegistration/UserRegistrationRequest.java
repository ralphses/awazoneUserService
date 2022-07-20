package net.awazone.awazoneUserService.registration.newRegistration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationRequest {

    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String emailAddress;
    private String userName;
    private String refererCode;

}
