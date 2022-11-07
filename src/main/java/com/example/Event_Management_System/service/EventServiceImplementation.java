package com.example.Event_Management_System.service;

import com.example.Event_Management_System.entities.Event;
import com.example.Event_Management_System.entities.EventCategory;
import com.example.Event_Management_System.entities.EventOrganizer;
import com.example.Event_Management_System.model.dto.EventDto;
import com.example.Event_Management_System.repository.EventCategoryRepository;
import com.example.Event_Management_System.repository.EventOrganizerRepository;
import com.example.Event_Management_System.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zulfa Attar
 */
@Service
public class EventServiceImplementation implements EventService{

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventCategoryRepository eventCategoryRepository;

    @Autowired
    EventOrganizerRepository eventOrganizerRepository;

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll().stream().filter(event -> !event.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public Event insertEvent(EventDto eventDto) {
        Event event = new Event();

        event.setEventName(eventDto.getEventName());
        event.setEventVenue(eventDto.getEventVenue());
        event.setEventCity(eventDto.getEventCity());
        event.setEventVenueCapacity(eventDto.getEventVenueCapacity());
        event.setEventBasePrice(eventDto.getEventBasePrice());
        event.setPricePerPerson(eventDto.getPricePerPerson());

        EventCategory eventCategory = eventCategoryRepository.findById(eventDto.getEventCategory()).orElse(null);
        event.setEventCategory(eventCategory);

        EventOrganizer eventOrganizer = eventOrganizerRepository.findById(eventDto.getEventOrganizer()).orElse(null);
        event.setEventOrganizer(eventOrganizer);

        System.out.println(event.getEventName());
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(EventDto eventDto) {
        Event event = eventRepository.findById(eventDto.getEventId()).orElse(null);

        if(event!= null) {
            event.setEventName(eventDto.getEventName());
            event.setEventVenue(eventDto.getEventVenue());
            event.setEventCity(eventDto.getEventCity());
            event.setEventVenueCapacity(eventDto.getEventVenueCapacity());
            event.setEventBasePrice(eventDto.getEventBasePrice());
            event.setPricePerPerson(eventDto.getPricePerPerson());

            EventCategory eventCategory = eventCategoryRepository.findById(eventDto.getEventCategory()).orElse(null);
            event.setEventCategory(eventCategory);

            EventOrganizer eventOrganizer = eventOrganizerRepository.findById(eventDto.getEventOrganizer()).orElse(null);
            event.setEventOrganizer(eventOrganizer);

            return eventRepository.save(event);
        }else
            return null;
    }

    @Override
    public List<Event> getEvents(long eventOrganizerId) {

        EventOrganizer eventOrganizer = eventOrganizerRepository.findById(eventOrganizerId).orElse(null);
        return eventRepository.findByEventOrganizer(eventOrganizer).stream().filter(event ->
                !event.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public void deleteEvent(long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        event.setDeleted(true);
        eventRepository.save(event);
    }

    @Override
    public List<Event> filterEventsByPrice(float minPrice, float maxPrice) {
        return eventRepository.findAll().stream().filter(event -> (event.getEventBasePrice()>=minPrice) && (event.getEventBasePrice()<=maxPrice)).collect(Collectors.toList());

    }

    @Override
    public List<Event> filterEventsByRating(float rating) {
        return eventRepository.findAll().stream().filter(event -> event.getAverageFeedbackRating() >= rating).collect(Collectors.toList());
    }

    @Override
    public List<Event> filterByCategory(String eventCategoryName) {
        EventCategory eventCategory = eventCategoryRepository.findByCategoryName(eventCategoryName).orElse(null);
        return eventRepository.findAll().stream().filter(event -> (event.getEventCategory().equals(eventCategory))).collect(Collectors.toList());


    }



}
