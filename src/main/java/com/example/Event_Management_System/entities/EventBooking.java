package com.example.Event_Management_System.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * @author Zulfa Attar
 */
@Data
@Entity
@Table(name = "event_booking_details")
public class EventBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private long bookingId;

    @Column(name = "event_time")
    public Time eventTime;

    @Column(name = "event_start_date")
    public Date eventStartDate;

    @Column(name = "event_end_date")
    public Date eventEndDate;

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "number_of_guests")
    private int numberOfGuests;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_event_id")
    private Event event;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_customer_id")
    private Customer customer;

    @Column(name = "booking_status")
    private String bookingStatus;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_organiser_id")
    private EventOrganizer eventOrganizer;


}
