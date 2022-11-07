package com.example.Event_Management_System.controller;

import com.example.Event_Management_System.entities.Event;
import com.example.Event_Management_System.entities.EventBooking;
import com.example.Event_Management_System.model.dto.EventBookingDto;
import com.example.Event_Management_System.model.dto.EventDto;
import com.example.Event_Management_System.service.EventBookingService;
import com.example.Event_Management_System.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Zulfa Attar
 */
@CrossOrigin
@RestController
@RequestMapping("/eventOrganizer")
public class EventOrganizerController {

    @Autowired
    EventService eventService;

    @Autowired
    EventBookingService bookingService;

    @PostMapping(value = "/insertEvent")
    public ResponseEntity addEvent(@RequestBody EventDto eventdto) {
        try {
            return new ResponseEntity<>(Optional.of(eventService.insertEvent(eventdto)), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEvents/{eventOrganiserId}")
    public ResponseEntity<List<Event>> getEvents(@PathVariable Long eventOrganiserId) {
        try {
            List<Event> events = eventService.getEvents(eventOrganiserId);

            if(events.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(events, HttpStatus.OK);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateBooking/{bookingStatus}/{bookingId}")
    public ResponseEntity acceptOrDeclineEventBooking(@PathVariable String bookingStatus, @PathVariable Long bookingId) {
        try {
            return new ResponseEntity<>(Optional.of(bookingService.confirmBooking(bookingId, bookingStatus)), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/postponeBooking")
    public ResponseEntity postponeEventBooking(@RequestBody EventBookingDto eventBookingDto) {
        try {
            return new ResponseEntity<>(Optional.of(bookingService.postponeBooking(eventBookingDto)), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEventBookings/{eventOrganiserId}")
    public ResponseEntity getBookingsByEventOrganiser(@PathVariable Long eventOrganiserId) {
        try {
            List<EventBooking> bookings = bookingService.getBookingByEventOrganiser(eventOrganiserId);

            if(bookings.isEmpty()) {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity(bookings, HttpStatus.OK);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateEvent")
    public ResponseEntity updateEvent(@RequestBody EventDto eventdto) {
        try {
            return new ResponseEntity<>(Optional.of(eventService.updateEvent(eventdto)), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/deleteEvent/{eventId}")
    public ResponseEntity deleteEvent(@PathVariable Long eventId) {
        try {
            eventService.deleteEvent(eventId);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
