package com.erturk.springboot.todo.survey;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Survey {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String description;
    private List<Question> questions;
}
