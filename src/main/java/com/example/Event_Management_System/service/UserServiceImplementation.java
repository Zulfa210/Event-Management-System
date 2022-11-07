package com.example.Event_Management_System.service;

import com.example.Event_Management_System.entities.Admin;
import com.example.Event_Management_System.entities.Customer;
import com.example.Event_Management_System.entities.EventOrganizer;
import com.example.Event_Management_System.entities.User;
import com.example.Event_Management_System.model.dto.UserDto;
import com.example.Event_Management_System.repository.AdminRepository;
import com.example.Event_Management_System.repository.CustomerRepository;
import com.example.Event_Management_System.repository.EventOrganizerRepository;
import com.example.Event_Management_System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Zulfa Attar
 */
@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    EventOrganizerRepository eventOrganizerRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(UserDto userDto) {
        User user = new User();
        if (userDto.getUserRole().equalsIgnoreCase("event_Organizer")) {
            EventOrganizer eventOrganiser = new EventOrganizer();
            eventOrganiser.setEventOrganizerName(userDto.getUserName());
            eventOrganiser.setEventOrganizerPhone(userDto.getUserNumber());
            eventOrganiser.setEventOrganizerEmail(userDto.getUserEmail());
            eventOrganiser.setRole("EVENT_ORGANIZER");
            user.setUserRole("EVENT_ORGANIZER");
            eventOrganizerRepository.save(eventOrganiser);
        } else if (userDto.getUserRole().equalsIgnoreCase("admin")) {
            Admin admin = new Admin();
            admin.setAdminName(userDto.getUserName());
            admin.setAdminPhoneNumber(userDto.getUserNumber());
            admin.setAdminEmail(userDto.getUserEmail());
            admin.setRole("ADMIN");
            user.setUserRole("ADMIN");
            adminRepository.save(admin);
        } else if (userDto.getUserRole().equalsIgnoreCase("customer")) {
            Customer customer = new Customer();
            customer.setCustomerName(userDto.getUserName());
            customer.setCustomerPhoneNo(userDto.getUserNumber());
            customer.setCustomerEmail(userDto.getUserEmail());
            customer.setRole("CUSTOMER");
            user.setUserRole("CUSTOMER");

            customerRepository.save(customer);
        }

        //to save user
        user.setUserEmail(userDto.getUserEmail());
        user.setUserPassword(userDto.getUserPassword());
        return userRepository.save(user);


    }

    @Override
    public ResponseEntity loginUser(User user) {
        User foundUser = userRepository.findByUserEmail(user.getUserEmail());

        if (foundUser != null) {
            if (user.getUserPassword().equals(foundUser.getUserPassword())) {

                try {
                    if (foundUser.getUserRole().equals("ADMIN")) {
                        Admin adminUser = adminRepository.findByAdminEmail(user.getUserEmail());
                        return new ResponseEntity(Optional.of(adminUser), HttpStatus.OK);
                    } else if (foundUser.getUserRole().equals("EVENT_ORGANIZER")) {
                        EventOrganizer eventOrganizerUser = eventOrganizerRepository.findByEventOrganizerEmail(user.getUserEmail());
                        return new ResponseEntity(Optional.of(eventOrganizerUser), HttpStatus.OK);
                    } else if (foundUser.getUserRole().equals("CUSTOMER")) {
                        Customer customerUser = customerRepository.findByCustomerEmail(user.getUserEmail());
                        return new ResponseEntity(Optional.of(customerUser), HttpStatus.OK);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);

    }
}
