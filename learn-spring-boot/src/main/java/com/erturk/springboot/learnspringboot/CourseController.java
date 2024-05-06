package com.erturk.springboot.learnspringboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {

    @RequestMapping("/courses")
    public List<Course> retrieveAllCourses() {
        return Arrays.asList(
                new Course(1, "AWS Fundamentals", "Coursera"),
                new Course(2, "Java Spring Boot", "O'Reilly"),
                new Course(3, "Data Structures and Algorithms", "Algolab"),
                new Course(4, "Go Programming", "Udemy"),
                new Course(5, "C++ Programming", "Coursera")
        );
    }
}
