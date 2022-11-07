package com.example.Event_Management_System.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zulfa Attar
 */
@Data
@Entity
@Table(name = "Event_Category")
public class EventCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id" , nullable = false,updatable = false)
    private long categoryId;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @JsonIgnore
    @OneToMany(mappedBy = "eventCategory", cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();
}
