package com.erturk.springboot.todo.survey;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
    @Id
    @GeneratedValue
    private int id;
    private String description;
    private List<String> options;
    private String correctAnswer;
}