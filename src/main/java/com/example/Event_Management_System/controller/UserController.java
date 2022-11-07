package com.example.Event_Management_System.controller;

import com.example.Event_Management_System.entities.User;
import com.example.Event_Management_System.model.dto.UserDto;
import com.example.Event_Management_System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Zulfa Attar
 */

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity registerUser(@RequestBody UserDto userDto){
        try {
            return new ResponseEntity<>(Optional.of(userService.registerUser(userDto)), HttpStatus.CREATED);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/loginUser")
    public ResponseEntity loginUser(@RequestBody User user){
        return userService.loginUser(user);
    }

}
