package net.awazone.awazoneUserService.registration.location;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LocationRequest {

    private String addressLine;
    private String district;
    private String city;
    private String state;
    private String country;
}
