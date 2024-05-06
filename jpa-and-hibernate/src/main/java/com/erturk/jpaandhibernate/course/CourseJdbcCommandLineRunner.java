package com.erturk.jpaandhibernate.course;

import com.erturk.jpaandhibernate.course.jdbc.CourseJdbcRepository;
import com.erturk.jpaandhibernate.course.jpa.CourseJpaRepository;
import com.erturk.jpaandhibernate.course.springdatajpa.CourseSpringDataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {
    //@Autowired
    //private CourseJdbcRepository repository;

    //@Autowired
    //private CourseJpaRepository repository;

    @Autowired
    private CourseSpringDataJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        //repository.save(new Course(4, "Kafka", "Erturk"));
        //repository.deleteById(4l);
        System.out.println(repository.findById(5l));;
        System.out.println(repository.findAll());
        System.out.println("Number of entries: "+repository.count());
        System.out.println(repository.findByAuthor("Erturk"));
    }
}
