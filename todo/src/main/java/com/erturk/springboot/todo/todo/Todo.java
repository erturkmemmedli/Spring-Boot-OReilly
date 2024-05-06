package com.erturk.springboot.todo.todo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Todo {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    @Size(min=10, message = "There Must Be At Least 10 Characters")
    private String description;
    private LocalDate targetDate;
    private boolean done;
}
