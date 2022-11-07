package com.example.Event_Management_System.service;

import com.example.Event_Management_System.entities.Customer;
import com.example.Event_Management_System.entities.EventBooking;
import com.example.Event_Management_System.entities.User;
import com.example.Event_Management_System.model.dto.EventBookingDto;

import java.util.List;

/**
 * @author Zulfa Attar
 */
public interface CustomerService {


    Customer updateCustomerDetails(Customer customer);
    User changePassword(String oldPassword, String newPassword);

    List<Customer> getAllCustomers();

}
