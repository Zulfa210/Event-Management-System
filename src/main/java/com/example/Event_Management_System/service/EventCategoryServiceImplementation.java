package com.example.Event_Management_System.service;

import com.example.Event_Management_System.entities.EventCategory;
import com.example.Event_Management_System.repository.EventCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zulfa Attar
 */
@Service
public class EventCategoryServiceImplementation implements EventCategoryService{

    @Autowired
    EventCategoryRepository eventCategoryRepository;

    @Override
    public EventCategory insertEventCategory(EventCategory eventCategory) {
        return eventCategoryRepository.save(eventCategory);
    }

    @Override
    public List<EventCategory> getAllEventCategories() {
        return eventCategoryRepository.findAll().stream().filter(eventCategory -> !eventCategory.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public EventCategory updateEventCategory(EventCategory eventCategory) {

        EventCategory existingCategory = eventCategoryRepository.findById(eventCategory.getCategoryId()).orElse(null);

        if(existingCategory != null) {
            existingCategory.setCategoryName(eventCategory.getCategoryName());
            //existingCategory.setEvents(eventCategory.getEvents());
        }

        return eventCategoryRepository.save(existingCategory);
    }

    @Override
    public void deleteEventCategory(long categoryId) {
        EventCategory category = eventCategoryRepository.findById(categoryId).orElse(null);
        category.setDeleted(true);
        eventCategoryRepository.save(category);
    }
}
