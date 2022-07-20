package net.awazone.awazoneUserService.repository;

import net.awazone.awazoneUserService.entity.EmailVerify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerifyRepository extends JpaRepository<EmailVerify, Long> {
}
