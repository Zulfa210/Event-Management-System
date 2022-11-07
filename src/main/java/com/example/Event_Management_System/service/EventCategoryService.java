package com.example.Event_Management_System.service;

import com.example.Event_Management_System.entities.EventCategory;

import java.util.List;

/**
 * @author Zulfa Attar
 */
public interface EventCategoryService {

    public List<EventCategory> getAllEventCategories();
    public EventCategory insertEventCategory(EventCategory eventCategory);
    public EventCategory updateEventCategory(EventCategory eventCategory);
    public void deleteEventCategory(long categoryId);

}
