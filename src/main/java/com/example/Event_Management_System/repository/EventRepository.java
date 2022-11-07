package com.example.Event_Management_System.repository;

import com.example.Event_Management_System.entities.Event;
import com.example.Event_Management_System.entities.EventOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Zulfa Attar
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByEventOrganizer(EventOrganizer eventOrganizer);

}
