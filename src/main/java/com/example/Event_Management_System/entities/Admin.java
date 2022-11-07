package com.example.Event_Management_System.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Zulfa Attar
 */

@Data
@Entity
@Table(name = "Admin_details")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id" , nullable = false, updatable = false)
    public long adminId;

    @Column(name = "admin_name", nullable = false)
    public String adminName;

    @Column(name = "admin_email", nullable = false)
    public String adminEmail;

    @Column(name = "admin_phone", nullable = false)
    public long adminPhoneNumber;

    private String role;

}
