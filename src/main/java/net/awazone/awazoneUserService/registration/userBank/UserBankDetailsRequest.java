package net.awazone.awazoneUserService.registration.userBank;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserBankDetailsRequest {

    private String accountNumber;
    private String accountType;
    private String accountName;
    private String bankName;
}
