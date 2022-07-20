package net.awazone.awazoneUserService.registration.userBank;

import lombok.AllArgsConstructor;
import net.awazone.awazoneUserService.entity.BankDetails;
import net.awazone.awazoneUserService.service.BankDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("ap1/v1/user/bank")
public class BankDetailsRequestController {

    private final BankDetailsService bankDetailsService;

    @PostMapping(path = "{userId}")
    public void saveBankDetails(@PathVariable Long userId, @RequestBody UserBankDetailsRequest userBankDetailsRequest) {
        bankDetailsService.saveBankDetails(userId, userBankDetailsRequest);
    }

    @PutMapping(path = "{userId}")
    public void updateBankDetails(@PathVariable Long userId, @RequestBody UserBankDetailsRequest userBankDetailsRequest) {
        bankDetailsService.updateBankDetails(userId, userBankDetailsRequest);
    }

    @GetMapping(path = "userId")
    public BankDetails getUserBankDetails(@PathVariable Long userId) {
        return bankDetailsService.getUserBankDetails(userId);
    }
}
