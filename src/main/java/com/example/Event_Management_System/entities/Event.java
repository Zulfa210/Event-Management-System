package com.example.Event_Management_System.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zulfa Attar
 */
@Data
@Entity
@Table(name = "event_details")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private long eventId;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_Venue")
    private String eventVenue;

    @Column(name = "event_city")
    private String eventCity;

    @Column(name = "event_venue_capacity")
    private int eventVenueCapacity;

    @Column(name = "event_base_price")
    private float eventBasePrice;

    @Column(name = "price_per_person")
    private float pricePerPerson;



    @JsonIgnore
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_category_id")
   // @JsonIgnore
    private EventCategory eventCategory;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_organizer_id")
    private EventOrganizer eventOrganizer;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<EventBooking> eventBookings = new ArrayList<>();


    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "rating")
    @ColumnDefault("0.0f")
    private float averageFeedbackRating;

}




