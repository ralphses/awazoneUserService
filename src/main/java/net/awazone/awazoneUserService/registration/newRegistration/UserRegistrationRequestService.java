package net.awazone.awazoneUserService.registration.newRegistration;

import lombok.AllArgsConstructor;
import net.awazone.awazoneUserService.entity.EmailVerify;
import net.awazone.awazoneUserService.entity.User;
import net.awazone.awazoneUserService.registration.updateRegistration.UpdateRegistrationRequest;
import net.awazone.awazoneUserService.repository.EmailVerifyRepository;
import net.awazone.awazoneUserService.repository.UserRepository;
import net.awazone.awazoneUserService.service.UserService;
import net.awazone.awazoneUserService.utils.UserEmailValidation;

import net.bytebuddy.utility.RandomString;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserRegistrationRequestService {

    private static final String USER_EXISTS_MESSAGE = "User with this email %s already exist";
    private static final String USER_DOES_NOT_EXISTS_MESSAGE = "User with %s does not exist";
    private final UserService userService;
    private  final UserRepository userRepository;
    private final JavaMailSender javaMailSender;
    private final EmailVerifyRepository emailVerifyRepository;

    @Transactional
    public void signupUser(UserRegistrationRequest userRegistrationRequest) {

        boolean userExist = userRepository.findByEmailAddress(userRegistrationRequest.getEmailAddress()).isPresent();
        if(userExist || !(Objects.equals(userRegistrationRequest.getPassword(), userRegistrationRequest.getConfirmPassword()))) {
            throw new IllegalStateException(String.format(USER_EXISTS_MESSAGE, userRegistrationRequest.getEmailAddress()));
        }

        String code = RandomString.make(64);

        User newUser = User.builder()
                .firstName(userRegistrationRequest.getFirstName())
                .lastName(userRegistrationRequest.getLastName())
                .emailAddress(userRegistrationRequest.getEmailAddress())
                .referer(userRepository.findByRefererCode(userRegistrationRequest.getRefererCode()).orElse(null))
                .password(userRegistrationRequest.getPassword())
                .userName(userRegistrationRequest.getUserName())
                .build();

        UserEmailValidation userEmailValidation = new UserEmailValidation(javaMailSender);

        try {
            userEmailValidation.sendVerificationMail(userRegistrationRequest.getEmailAddress(), code);
            userService.saveUser(newUser);

            emailVerifyRepository.save(
                    EmailVerify.builder()
                            .code(code)
                            .user(newUser)
                            .build());

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByIdNumber(String idNumber) {
        User user = userRepository.findByIdNumber(idNumber).orElseThrow(() -> new IllegalStateException(String.format(USER_DOES_NOT_EXISTS_MESSAGE, idNumber)));
        return user;
    }

    @Transactional
    public void updateUserRegistration(Long userId, UpdateRegistrationRequest updateRegistrationRequest) {
        userService.updateUser(userId, updateRegistrationRequest);
    }

    @Transactional
    public boolean verifyUserEmail(String code) {
        return true;
    }
}
