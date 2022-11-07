package com.example.Event_Management_System.repository;

import com.example.Event_Management_System.entities.EventOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zulfa Attar
 */
@Repository
public interface EventOrganizerRepository extends JpaRepository<EventOrganizer, Long> {

    EventOrganizer findByEventOrganizerEmail(String userEmail);
}
