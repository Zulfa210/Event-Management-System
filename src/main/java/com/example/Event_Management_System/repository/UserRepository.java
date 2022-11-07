package com.example.Event_Management_System.repository;

import com.example.Event_Management_System.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Zulfa Attar
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserPassword(String userPassword);

    User findByUserEmail(String userEmail);
}
