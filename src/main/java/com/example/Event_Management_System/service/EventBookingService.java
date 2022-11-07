package com.example.Event_Management_System.service;

import com.example.Event_Management_System.entities.EventBooking;
import com.example.Event_Management_System.model.dto.EventBookingDto;

import java.util.List;

/**
 * @author Zulfa Attar
 */
public interface EventBookingService {
    public EventBooking addEventBooking(EventBookingDto eventBookingDto);
    public List<EventBooking> getBookings();

    public EventBooking confirmBooking(long bookingId, String bookingStatus);

    public void cancelBooking(long bookingId);
    public EventBooking postponeBooking(EventBookingDto eventBookingDto);

    List<EventBooking> getBookingByEventOrganiser(Long eventOrganiserId);

    List<EventBooking> getBookingByCustomer(Long customerId);

}
