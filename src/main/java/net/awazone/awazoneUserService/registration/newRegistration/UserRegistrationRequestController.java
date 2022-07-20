package net.awazone.awazoneUserService.registration.newRegistration;

import lombok.AllArgsConstructor;
import net.awazone.awazoneUserService.entity.User;
import net.awazone.awazoneUserService.registration.updateRegistration.UpdateRegistrationRequest;
import net.awazone.awazoneUserService.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserRegistrationRequestController {

    private final UserRegistrationRequestService userRegistrationRequestService;
    private final UserService userService;

    @PostMapping
    public void createNewUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        userRegistrationRequestService.signupUser(userRegistrationRequest);
    }

    @GetMapping("/verify")
    public boolean verifyUser(@Param("code") String code) {
        return userRegistrationRequestService.verifyUserEmail(code);
    }

    @GetMapping(path = "{idNumber}")
    public User getUser(@PathVariable String idNumber) {
        return userRegistrationRequestService.getUserByIdNumber(idNumber);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(
            @RequestBody UpdateRegistrationRequest updateRegistrationRequest,
            @PathVariable Long userId
    ) {
        userRegistrationRequestService.updateUserRegistration(userId, updateRegistrationRequest);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/login")
    public User login (
            @RequestParam(required = true) String emailAddress,
            @RequestParam(required = true) String password
                      )
    {
        return userService.loginUser(emailAddress, password);
    }

}
