package net.awazone.awazoneUserService.repository;

import net.awazone.awazoneUserService.entity.KycRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KycRecordRepository extends JpaRepository<KycRecord, Long> {
}
