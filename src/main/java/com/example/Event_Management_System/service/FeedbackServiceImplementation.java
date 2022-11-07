package com.example.Event_Management_System.service;

import com.example.Event_Management_System.entities.Customer;
import com.example.Event_Management_System.entities.Event;
import com.example.Event_Management_System.entities.Feedback;
import com.example.Event_Management_System.model.dto.FeedbackDto;
import com.example.Event_Management_System.repository.CustomerRepository;
import com.example.Event_Management_System.repository.EventRepository;
import com.example.Event_Management_System.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zulfa Attar
 */
@Service
public class FeedbackServiceImplementation  implements FeedbackService{

    @Autowired
    EventRepository eventRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Feedback giveFeedback(FeedbackDto feedbackDto) {
        Feedback provideFeedback = new Feedback();
        System.out.println(feedbackDto.getFeedBackRating());

        //from feedback-dto
        provideFeedback.setFeedbackComment(feedbackDto.getFeedbackComment());
        provideFeedback.setFeedbackRating(feedbackDto.getFeedBackRating());

        Event event = eventRepository.findById(feedbackDto.getEventId()).orElse(null);
        List<Feedback> eventFeedbacks = feedbackRepository.findByEvent(event);

        float sumOfRatings = 0;
        for(Feedback feedback: eventFeedbacks){
            sumOfRatings += feedback.getFeedbackRating();
        }
        sumOfRatings += provideFeedback.getFeedbackRating();
        float averageFeedback = sumOfRatings/(eventFeedbacks.size()+1);
        event.setAverageFeedbackRating(averageFeedback);
        provideFeedback.setEvent(event);

        Customer customer = customerRepository.findById(feedbackDto.getCustomerId()).orElse(null);
        provideFeedback.setCustomer(customer);

        System.out.println(provideFeedback.getFeedbackRating());
        return feedbackRepository.save(provideFeedback);


    }

    @Override
    public List<Feedback> getFeedbacks(long eventId) {

        Event event = eventRepository.findById(eventId).orElse(null);
        return feedbackRepository.findByEvent(event);
    }
}
