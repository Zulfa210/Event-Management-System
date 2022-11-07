package com.example.Event_Management_System.service;

import com.example.Event_Management_System.entities.EventOrganizer;
import com.example.Event_Management_System.repository.EventOrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zulfa Attar
 */
@Service
public class EventOrganizerServiceImplementation implements EventOrganizerService{

    @Autowired
    EventOrganizerRepository eventOrganizerRepository;

    @Override
    public List<EventOrganizer> getAllEventOrganizers() {
        return eventOrganizerRepository.findAll().stream().filter(eventOrganizer -> !eventOrganizer.isDeleted()).collect(Collectors.toList());
    }
}
