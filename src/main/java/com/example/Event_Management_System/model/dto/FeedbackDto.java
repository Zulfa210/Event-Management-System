package com.example.Event_Management_System.model.dto;

import lombok.Data;

/**
 * @author Zulfa Attar
 */
@Data
public class FeedbackDto {

    private String feedbackComment;
    private float feedBackRating;
    private Long eventId;
    private Long customerId;


}
