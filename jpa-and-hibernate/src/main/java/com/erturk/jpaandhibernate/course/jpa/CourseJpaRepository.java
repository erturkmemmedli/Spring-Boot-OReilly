package com.erturk.jpaandhibernate.course.jpa;

import com.erturk.jpaandhibernate.course.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional

public class CourseJpaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void insert(Course course) {
        entityManager.merge(course);
    }

    public void deleteById(Integer id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    public Course findById(Integer id) {
        return entityManager.find(Course.class, id);
    }
}
