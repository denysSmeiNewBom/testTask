package com.example.bootuserstest.repository;

import com.example.bootuserstest.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findStudentByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.phoneNumber = ?1")
    Optional<User> findStudentByPhoneNumber(String phoneNumber);

    @Query
    Optional<User> findByPhoneNumberOrEmail(String phoneNumber, String email);
}
