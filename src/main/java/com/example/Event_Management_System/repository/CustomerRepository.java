package com.example.Event_Management_System.repository;

import com.example.Event_Management_System.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zulfa Attar
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCustomerEmail(String userEmail);
}
