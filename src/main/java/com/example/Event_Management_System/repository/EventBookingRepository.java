package com.example.Event_Management_System.repository;

import com.example.Event_Management_System.entities.Customer;
import com.example.Event_Management_System.entities.EventBooking;
import com.example.Event_Management_System.entities.EventOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Zulfa Attar
 */
@Repository
public interface EventBookingRepository extends JpaRepository<EventBooking, Long> {
    List<EventBooking> findByEventOrganizer(EventOrganizer eventOrganizer);

    List<EventBooking> findByCustomer(Customer customer);

}
