package net.awazone.awazoneUserService.service;

import lombok.AllArgsConstructor;

import net.awazone.awazoneUserService.entity.BankDetails;
import net.awazone.awazoneUserService.entity.User;
import net.awazone.awazoneUserService.registration.userBank.UserBankDetailsRequest;
import net.awazone.awazoneUserService.repository.BankDetailsRepository;
import net.awazone.awazoneUserService.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BankDetailsService {

    private final UserRepository userRepository;
    private final BankDetailsRepository bankDetailsRepository;

    @Transactional
    public void saveBankDetails(Long userId, UserBankDetailsRequest userBankDetailsRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("Invalid User"));

        BankDetails userBankDetails = BankDetails.builder()
                .accountName(userBankDetailsRequest.getAccountName())
                .bankName(userBankDetailsRequest.getBankName())
                .accountNumber(userBankDetailsRequest.getAccountNumber())
                .accountType(userBankDetailsRequest.getAccountType())
                .build();

        bankDetailsRepository.save(userBankDetails);
        user.setBankDetails(userBankDetails);
    }

    @Transactional
    public void updateBankDetails(Long userId, UserBankDetailsRequest userBankDetailsRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("Invalid User"));

        user.getBankDetails().setBankName(userBankDetailsRequest.getBankName());
        user.getBankDetails().setAccountName(userBankDetailsRequest.getAccountName());
        user.getBankDetails().setAccountNumber(userBankDetailsRequest.getAccountNumber());
        user.getBankDetails().setAccountType(userBankDetailsRequest.getAccountType());

    }

    @Transactional
    public BankDetails getUserBankDetails(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("Invalid User"));
        return user.getBankDetails();
    }
}