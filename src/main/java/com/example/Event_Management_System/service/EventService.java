package com.example.Event_Management_System.service;

import com.example.Event_Management_System.entities.Event;
import com.example.Event_Management_System.model.dto.EventDto;

import java.sql.Date;
import java.util.List;

/**
 * @author Zulfa Attar
 */
public interface EventService {

public List<Event> getAllEvents();

public Event insertEvent(EventDto eventDto);

public Event updateEvent(EventDto eventDto);
public List<Event> getEvents(long eventOrganizerId);

public void deleteEvent(long eventId);

List<Event> filterEventsByPrice(float minPrice, float maxPrice);
List<Event> filterEventsByRating(float rating);
List<Event> filterByCategory(String eventCategoryName);



}
