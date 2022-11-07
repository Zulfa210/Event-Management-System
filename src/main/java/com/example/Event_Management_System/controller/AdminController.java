package com.example.Event_Management_System.controller;

import com.example.Event_Management_System.entities.Customer;
import com.example.Event_Management_System.entities.Event;
import com.example.Event_Management_System.entities.EventOrganizer;
import com.example.Event_Management_System.repository.EventOrganizerRepository;
import com.example.Event_Management_System.service.CustomerService;
import com.example.Event_Management_System.service.EventCategoryService;
import com.example.Event_Management_System.service.EventOrganizerService;
import com.example.Event_Management_System.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import com.example.Event_Management_System.entities.EventCategory;

/**
 * @author Zulfa Attar
 */

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    EventCategoryService eventCategoryService;

    @Autowired
    EventService eventService;

    @Autowired
    EventOrganizerService eventOrganizerService;

    @Autowired
    CustomerService customerService;

    @GetMapping("/getEventCategories")
    public ResponseEntity getEventCategory(){
        try {
            List<EventCategory> eventCategories = eventCategoryService.getAllEventCategories();
            if (eventCategories.size() > 0) {
                return new ResponseEntity(eventCategories, HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/insertEventCategory")
    public ResponseEntity insertEventCategory(@RequestBody EventCategory eventCategory){
        try{
            return new ResponseEntity(Optional.of(eventCategoryService.insertEventCategory(eventCategory)), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/updateEventCategory")
    public ResponseEntity updateEventCategory(@RequestBody EventCategory eventCategory){
        try{
            return new ResponseEntity(Optional.of(eventCategoryService.updateEventCategory(eventCategory)), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEventCategory/{eventCategoryId}")
    public ResponseEntity deleteEventCategory(@PathVariable long eventCategoryId){
        try{
            eventCategoryService.deleteEventCategory(eventCategoryId);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEvents")
    public ResponseEntity getAllEvents() {
        try {
            List<Event> events = eventService.getAllEvents();

            if(events.isEmpty()) {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity(events, HttpStatus.OK);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEventOrganizers")
    public ResponseEntity getAllEventOrganizer() {
        try {
            List<EventOrganizer> events = eventOrganizerService.getAllEventOrganizers();

            if(events.isEmpty()) {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity(events, HttpStatus.OK);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCustomers")
    public ResponseEntity getCustomers() {
        try {
            List<Customer> customers = customerService.getAllCustomers();

            if(customers.isEmpty()) {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity(customers, HttpStatus.OK);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
