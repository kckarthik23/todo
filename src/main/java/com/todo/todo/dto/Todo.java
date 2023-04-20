package com.todo.todo.dto;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Todo {

    @Id
    private int id;
    private boolean done;
    private String description;
    private LocalDate createdDate;
    private int priority;
    private String username;

}
