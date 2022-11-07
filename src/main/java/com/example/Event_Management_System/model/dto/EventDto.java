package com.example.Event_Management_System.model.dto;

import lombok.Data;

/**
 * @author Zulfa Attar
 */
@Data
public class EventDto {

    private long eventId;
    private String eventName;
    private String eventVenue;
    private String eventCity;
    private int eventVenueCapacity;
    public float eventBasePrice;
    public float pricePerPerson;
    private long eventCategory;
    private long eventOrganizer;


}
