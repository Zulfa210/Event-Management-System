package com.example.Event_Management_System.controller;

import com.example.Event_Management_System.entities.Customer;
import com.example.Event_Management_System.entities.Event;
import com.example.Event_Management_System.entities.EventBooking;
import com.example.Event_Management_System.entities.User;
import com.example.Event_Management_System.model.dto.EventBookingDto;
import com.example.Event_Management_System.model.dto.FeedbackDto;
import com.example.Event_Management_System.service.CustomerService;
import com.example.Event_Management_System.service.EventBookingService;
import com.example.Event_Management_System.service.EventService;
import com.example.Event_Management_System.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Zulfa Attar
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    EventBookingService eventBookingService;

    @Autowired
    EventService eventService;

    @Autowired
    CustomerService customerService;

    @Autowired
    FeedbackService feedbackService;


    @PostMapping(value = "/addEventBooking")
    public ResponseEntity addEventBooking(@RequestBody EventBookingDto bookingDto) {
        try {
            return new ResponseEntity<>(Optional.of(eventBookingService.addEventBooking(bookingDto)), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/cancelEventBooking/{bookingId}")
    public ResponseEntity cancelEventBooking(@PathVariable Long bookingId) {
        try {
            eventBookingService.cancelBooking(bookingId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEventBookings/{customerId}")
    public ResponseEntity getBookingsByCustomer(@PathVariable Long customerId) {
        try {
            List<EventBooking> bookings = eventBookingService.getBookingByCustomer(customerId);

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


    @GetMapping("/filterByPrice/{minPrice}/{maxPrice}")
    public ResponseEntity<List<Event>> filterByEventPrice(@PathVariable float minPrice, @PathVariable float maxPrice) {
        try {
            return new ResponseEntity(Optional.of(eventService.filterEventsByPrice(minPrice, maxPrice)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filterByRating/{rating}")
    public ResponseEntity<List<Event>> filterByRating(@PathVariable float rating) {
        try {
            return new ResponseEntity(Optional.of(eventService.filterEventsByRating(rating)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEvents/{eventCategoryName}")
    public ResponseEntity<List<Event>> filterByEventCategory(@PathVariable String eventCategoryName) {
        try {
            return new ResponseEntity(Optional.of(eventService.filterByCategory(eventCategoryName)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/giveFeedback")
    public ResponseEntity addCustomerFeedback(@RequestBody FeedbackDto feedbackDto) {
        try {
            return new ResponseEntity<>(Optional.of(feedbackService.giveFeedback(feedbackDto)), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEvents")
    public ResponseEntity getBookingsByEventOrganiser() {
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

    @PutMapping("/updateCustomerDetails")
    public ResponseEntity updateCustomerDetails(@RequestBody Customer customer) {
        try {
            return new ResponseEntity(Optional.of(customerService.updateCustomerDetails(customer)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/changePassword/{oldPassword}/{newPassword}")
    public ResponseEntity changePassword(@PathVariable String oldPassword, @PathVariable String newPassword){
        try {

            User user= customerService.changePassword(oldPassword,newPassword);
            if(user != null) {
                return new ResponseEntity(user, HttpStatus.OK);
            }else {
                return new ResponseEntity(new String("Invalid Password"), HttpStatus.OK);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
