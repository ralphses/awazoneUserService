package net.awazone.awazoneUserService.registration.newKycDetails;

import lombok.AllArgsConstructor;
import net.awazone.awazoneUserService.service.KycService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user/kyc")
@AllArgsConstructor
public class NewKycDetailsController {

    private final KycService kycService;

    @PostMapping(path = "userId")
    public void createKycRecord(@PathVariable Long userId, @RequestBody NewKycDetails newKycDetails) {
        kycService.addNewKycRecord(userId, newKycDetails);
    }

    @PutMapping(path = "userId")
    public void updateKycRecord(@PathVariable Long userId, @RequestBody NewKycDetails newKycDetails) {
        kycService.updateKycRecord(userId, newKycDetails);
    }

}
