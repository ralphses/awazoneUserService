package net.awazone.awazoneUserService.repository;

import net.awazone.awazoneUserService.entity.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, Long> {
}
