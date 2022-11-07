package com.example.Event_Management_System.model.dto;

import lombok.Data;

/**
 * @author Zulfa Attar
 */

@Data
public class UserDto {

    private long userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private long userNumber;
    private String userRole;


}
