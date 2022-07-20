package net.awazone.awazoneUserService.registration.location;

import lombok.AllArgsConstructor;
import net.awazone.awazoneUserService.entity.Location;
import net.awazone.awazoneUserService.service.LocationService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user/location")
public class LocationRequestController {

    private final LocationService locationService;

    @PostMapping(path = "{userId}")
    public void addUserLocation(@PathVariable Long userId, @RequestBody LocationRequest locationRequest) {
        locationService.addNewLocation(userId, locationRequest);
    }

    @PutMapping(path = "{userId}")
    public void updateLocation(@PathVariable Long userId, @RequestBody LocationRequest locationRequest) {
        locationService.updateLocation(userId, locationRequest);
    }

    @GetMapping(path = "{userId}")
    public Location getUserLocation(@PathVariable Long userId) {
        return locationService.getUserLocation(userId);
    }

}
