package net.awazone.awazoneUserService.registration.updateRegistration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateRegistrationRequestTest {

    @Test
    public void testUpdate() {
        UpdateRegistrationRequest updateRegistrationRequest = UpdateRegistrationRequest.builder()
                .heardAboutUs("")
                .gender("male")
                .occupation("Occupation").build();
        updateRegistrationRequest.setDateOfBirth("2021-12-12");

    }

}