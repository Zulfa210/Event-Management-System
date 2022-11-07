package com.example.Event_Management_System.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zulfa Attar
 */

@Entity
@Data
@Table(name = "event_organizer")
public class EventOrganizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organizer_id", updatable = false, nullable = false)
    private long eventOrganizerId;

    @Column(name = "organizer_name", nullable = false)
    private String eventOrganizerName;

    @Column(name = "organizer_email")
    private String eventOrganizerEmail;

    @Column(name = "organizer_phone")
    private long eventOrganizerPhone;

    @OneToMany(mappedBy = "eventOrganizer", cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "eventOrganizer", cascade = CascadeType.ALL)
    private List<EventBooking> bookings = new ArrayList<>();


    @Column(name = "is_deleted")
    private boolean isDeleted;

    private String role;
}
