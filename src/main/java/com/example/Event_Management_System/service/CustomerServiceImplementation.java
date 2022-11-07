package com.example.Event_Management_System.service;

import com.example.Event_Management_System.entities.Customer;
import com.example.Event_Management_System.entities.User;
import com.example.Event_Management_System.repository.CustomerRepository;
import com.example.Event_Management_System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zulfa Attar
 */

@Service
public class CustomerServiceImplementation implements CustomerService {


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Customer updateCustomerDetails(Customer customer) {
        Customer updatedCustomer = customerRepository.findById(customer.getCustomerId()).orElse(null);

        if(updatedCustomer != null) {
            updatedCustomer.setCustomerName(customer.getCustomerName());
            updatedCustomer.setCustomerPhoneNo(customer.getCustomerPhoneNo());
            return customerRepository.save(updatedCustomer);
        } else {
            return null;
        }

    }

    @Override
    public User changePassword(String oldPassword, String newPassword) {

        User user = userRepository.findByUserPassword(oldPassword).orElse(null);
        if(user != null) {
            if(user.getUserPassword().equals(oldPassword)) {
                user.setUserPassword(newPassword);
                return userRepository.save(user);
            }else {
                return null;
            }
        } else {
            return null;
        }

    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll().stream().filter(customer -> !customer.isDeleted()).collect(Collectors.toList());
    }
}
