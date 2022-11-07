package com.example.Event_Management_System.repository;

import com.example.Event_Management_System.entities.Admin;
import com.example.Event_Management_System.entities.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Zulfa Attar
 */
@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {
    Optional<EventCategory> findByCategoryName(String categoryName);

}
