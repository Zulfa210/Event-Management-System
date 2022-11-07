package com.example.Event_Management_System.service;

import com.example.Event_Management_System.entities.User;
import com.example.Event_Management_System.model.dto.UserDto;
import org.springframework.http.ResponseEntity;

/**
 * @author Zulfa Attar
 */
public interface UserService {
    User registerUser(UserDto userDto);
    ResponseEntity loginUser(User user);

}
