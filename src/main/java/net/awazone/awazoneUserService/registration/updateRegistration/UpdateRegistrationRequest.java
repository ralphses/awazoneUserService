package net.awazone.awazoneUserService.registration.updateRegistration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.awazone.awazoneUserService.entity.Gender;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class UpdateRegistrationRequest {

    private String heardAboutUs;
    private String occupation;
    private String gender;
    private String dateOfBirth;

}
