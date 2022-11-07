package com.example.Event_Management_System.repository;

import com.example.Event_Management_System.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Zulfa Attar
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByAdminEmail(String adminEmail);
}
