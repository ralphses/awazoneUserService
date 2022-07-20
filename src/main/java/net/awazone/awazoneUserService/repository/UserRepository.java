package net.awazone.awazoneUserService.repository;

import net.awazone.awazoneUserService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            value = "SELECT * FROM user WHERE referrer = ?1",
            nativeQuery = true
    )
    public Optional<User> findByRefererCode(String refererCode);
    @Query(
            value = "SELECT * FROM user WHERE id_number = ?1",
            nativeQuery = true
    )
    public Optional<User> findByIdNumber(String idNumber);
    public Optional<User> findByEmailAddress(String emailAddress);

    @Query(
            value = "UPDATE user SET current_credit = ?1 WHERE user_id = ?2",
            nativeQuery = true
    )
    @Modifying
    @Transactional
    public int updateUserCredit(Integer newCredit, Long id);
}
