package com.example.Event_Management_System.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zulfa Attar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_details")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", updatable = false, nullable = false)
    private long customerId;

    @Column(name = "customer_name", nullable = false)
    @NotNull
    private String customerName;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;


    @Column(name = "customer_phone_no")
    private long customerPhoneNo;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<EventBooking> eventBookings = new ArrayList<>();

    private String role;

}
