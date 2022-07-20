package net.awazone.awazoneUserService.service;

import lombok.AllArgsConstructor;
import net.awazone.awazoneUserService.entity.Gender;
import net.awazone.awazoneUserService.entity.User;
import net.awazone.awazoneUserService.registration.updateRegistration.UpdateRegistrationRequest;
import net.awazone.awazoneUserService.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user) {
        Optional<User> referrerUserOptional = Optional.ofNullable(user.getReferer());
        if(referrerUserOptional.isPresent()) {
            int newReferrerUserCredit = referrerUserOptional.get().getCurrentCredit() + 1;
            userRepository.updateUserCredit(newReferrerUserCredit, referrerUserOptional.get().getId());
            user.setReferer(referrerUserOptional.get());
        }
        String uniqueCode = generateUserIDNumber(user);

        user.setIdNumber(uniqueCode);
        user.setKycStatus(false);
        user.setLocked(true);
        user.setMyRefererCode(uniqueCode);

        userRepository.save(user);
    }


    public User getStudent(String idNumber) {
        User user = userRepository.findByIdNumber(idNumber)
                .orElseThrow(() -> new IllegalStateException("User does not exist!"));
        return user;
    }

    @Transactional
    public void updateUser(Long id, UpdateRegistrationRequest updateRegistrationRequest) {
        Gender userGender = (updateRegistrationRequest.getGender().equalsIgnoreCase("male")) ? Gender.MALE : Gender.FEMALE;

        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User does not exist"));

        user.setOccupation(updateRegistrationRequest.getOccupation());
        user.setDateOfBirth(LocalDate.parse(updateRegistrationRequest.getDateOfBirth()));
        user.setGender(userGender);
        user.setHeardAboutUs(updateRegistrationRequest.getHeardAboutUs());
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("Invalid ID"));
        userRepository.delete(user);
    }

    @Transactional
    public User loginUser(String emailAddress, String password) {
        User user = userRepository.findByEmailAddress(emailAddress).orElseThrow(() -> new IllegalStateException("User does not exist!"));
        if(Objects.equals(password, user.getPassword())) {
            return user;
        }
        return new User();
    }

    /**
     * PRIVATE METHODS
     */
    private static String generateUserIDNumber(User user) {
        return String.format("%s%d", user.getFirstName().toUpperCase(), new Random().nextInt());
    }

}
