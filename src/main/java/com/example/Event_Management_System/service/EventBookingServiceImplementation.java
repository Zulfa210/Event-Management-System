package com.example.Event_Management_System.service;

import com.example.Event_Management_System.entities.Customer;
import com.example.Event_Management_System.entities.Event;
import com.example.Event_Management_System.entities.EventBooking;
import com.example.Event_Management_System.entities.EventOrganizer;
import com.example.Event_Management_System.model.dto.EventBookingDto;
import com.example.Event_Management_System.repository.CustomerRepository;
import com.example.Event_Management_System.repository.EventBookingRepository;
import com.example.Event_Management_System.repository.EventOrganizerRepository;
import com.example.Event_Management_System.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * @author Zulfa Attar
 */
@Service
public class EventBookingServiceImplementation implements EventBookingService{

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventBookingRepository eventBookingRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EventOrganizerRepository eventOrganizerRepository;

    @Override
    public EventBooking addEventBooking(EventBookingDto eventBookingDto) {

        EventBooking addBooking = new EventBooking();

        addBooking.setEventStartDate(Date.valueOf(eventBookingDto.getBookingStartDate()));
        addBooking.setEventEndDate(Date.valueOf(eventBookingDto.getBookingEndDate()));
        addBooking.setNumberOfGuests(eventBookingDto.getTotalBookedSeats());
        addBooking.setEventTime(Time.valueOf(eventBookingDto.getEventTime()));

        Event event = eventRepository.findById(eventBookingDto.getEventId()).orElse(null);
        float bookingTotalPrice = (event.getEventBasePrice() + (event.getPricePerPerson() * eventBookingDto.getTotalBookedSeats()));
        addBooking.setTotalPrice(bookingTotalPrice);
        addBooking.setEvent(event);
        addBooking.setEventOrganizer(event.getEventOrganizer());


        Customer customer = customerRepository.findById(eventBookingDto.getCustomerId()).orElse(null);
        addBooking.setCustomer(customer);

        addBooking.setBookingStatus("PENDING");
        return eventBookingRepository.save(addBooking);

    }

    @Override
    public List<EventBooking> getBookings() {
        return eventBookingRepository.findAll();
    }


    @Override
    public EventBooking confirmBooking(long bookingId, String bookingStatus) {
        EventBooking foundBooking = eventBookingRepository.findById(bookingId).orElse(null);

        if(foundBooking != null) {
            if(bookingStatus.equalsIgnoreCase("ACCEPTED")) {
                foundBooking.setBookingStatus("ACCEPTED");
            } else {
                foundBooking.setBookingStatus("DECLINED");
            }
            return eventBookingRepository.save(foundBooking);
        } else {
            return null;
        }

    }



    @Override
    public void cancelBooking(long bookingId) {

        EventBooking eventBooking = eventBookingRepository.findById(bookingId).orElse(null);

        eventBooking.setBookingStatus("cancelled");
        eventBookingRepository.save(eventBooking);

    }

    @Override
    public EventBooking postponeBooking(EventBookingDto eventBookingDto) {
        EventBooking foundBooking = eventBookingRepository.findById(eventBookingDto.getBookingId()).orElse(null);

        if(foundBooking != null) {
            foundBooking.setBookingStatus("pending");
            foundBooking.setEventStartDate(Date.valueOf(eventBookingDto.getBookingStartDate()));
            foundBooking.setEventEndDate(Date.valueOf( eventBookingDto.getBookingEndDate()));
            return eventBookingRepository.save(foundBooking);
        } else {
            return null;
        }

    }

    @Override
    public List<EventBooking> getBookingByEventOrganiser(Long eventOrganiserId) {
            EventOrganizer eventOrganizer = eventOrganizerRepository.findById(eventOrganiserId).orElse(null);
            return eventBookingRepository.findByEventOrganizer(eventOrganizer);
        }

    @Override
    public List<EventBooking> getBookingByCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        return eventBookingRepository.findByCustomer(customer);
    }


}
