package net.awazone.awazoneUserService.service;

import lombok.AllArgsConstructor;
import net.awazone.awazoneUserService.entity.KycRecord;
import net.awazone.awazoneUserService.entity.User;
import net.awazone.awazoneUserService.registration.newKycDetails.NewKycDetails;
import net.awazone.awazoneUserService.repository.KycRecordRepository;
import net.awazone.awazoneUserService.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class KycService {

    private final KycRecordRepository kycRecordRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addNewKycRecord(Long userId, NewKycDetails newKycDetails) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User does not exist"));

        KycRecord userKycRecord = KycRecord.builder()
                .documentImgUrl(newKycDetails.getDocumentImgUrl())
                .status(false)
                .documentType(newKycDetails.getDocumentType())
                .build();

        kycRecordRepository.save(userKycRecord);
        user.setKycStatus(true);
        user.setKycRecord(userKycRecord);
    }

    @Transactional
    public void updateKycRecord(Long userId, NewKycDetails newKycDetails) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User does not exist"));
        user.getKycRecord().setDocumentType(newKycDetails.getDocumentType());
        user.getKycRecord().setDocumentImgUrl(newKycDetails.getDocumentImgUrl());
    }
}
