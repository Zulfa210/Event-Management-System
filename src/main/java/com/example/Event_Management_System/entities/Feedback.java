package com.example.Event_Management_System.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Zulfa Attar
 */

@Data
@Entity
@Table(name = "feedback_details")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id", updatable = false, nullable = false)
    private long feedbackId;

    @Lob
    @Column(name = "feedback_comment")
    private String feedbackComment;

    @Column(name = "feedback_rating")
    private float feedbackRating;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private Event event;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

}

