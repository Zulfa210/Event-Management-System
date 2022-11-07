package com.example.Event_Management_System.service;

import com.example.Event_Management_System.entities.Feedback;
import com.example.Event_Management_System.model.dto.FeedbackDto;

import java.util.List;

/**
 * @author Zulfa Attar
 */
public interface FeedbackService {

    public Feedback giveFeedback(FeedbackDto feedbackDto);

    public List<Feedback> getFeedbacks(long eventId);
}
