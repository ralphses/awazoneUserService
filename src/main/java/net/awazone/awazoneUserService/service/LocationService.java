package net.awazone.awazoneUserService.service;

import lombok.AllArgsConstructor;
import net.awazone.awazoneUserService.entity.Location;
import net.awazone.awazoneUserService.entity.User;
import net.awazone.awazoneUserService.registration.location.LocationRequest;
import net.awazone.awazoneUserService.repository.LocationRepository;
import net.awazone.awazoneUserService.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addNewLocation(Long userId, LocationRequest locationRequest) {
        User thisUser = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("Invalid Id"));

        Location location = Location.builder()
                .addressLine(locationRequest.getAddressLine())
                .city(locationRequest.getCity())
                .state(locationRequest.getState())
                .country(locationRequest.getCountry())
                .district(locationRequest.getDistrict())
                .build();

        locationRepository.save(location);
        thisUser.setLocation(location);

    }

    @Transactional
    public void updateLocation(Long userId, LocationRequest locationRequest) {
        User thisUser = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("Invalid Id"));

        thisUser.getLocation().setAddressLine(locationRequest.getAddressLine());
        thisUser.getLocation().setCity(locationRequest.getCity());
        thisUser.getLocation().setCountry(locationRequest.getCountry());
        thisUser.getLocation().setState(locationRequest.getState());
        thisUser.getLocation().setDistrict(locationRequest.getDistrict());

    }

    @Transactional
    public Location getUserLocation(Long userId) {
        User thisUser = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("Invalid Id"));
        return thisUser.getLocation();
    }
}
