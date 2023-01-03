package com.in28Minutes.jpa.hibernate.demo.repository;


import com.in28Minutes.jpa.hibernate.demo.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(CourseSpringDataRepositoryTest.class);

    @Autowired
    private CourseSpringDataRepository repository;


    @Test
    void testFindById_CoursePresent() {
        Optional<Course> optCourse = repository.findById(10001L);
        logger.info("Course is present: {}", optCourse.isPresent());
        assertTrue(optCourse.isPresent());
    }

    @Test
    void testFindById_CourseNoPresent() {
        Optional<Course> optCourse = repository.findById(90001L);
        logger.info("Course is present: {}", optCourse.isPresent());
        assertFalse(optCourse.isPresent());
    }

    @Test
    public void playingAroundWithSpringDataRepository() {
        //Course course = new Course("Microservices in 100 Steps");
        //repository.save(course);

        //course.setName("Microservices in 100 Steps - Updated");
        //repository.save(course);
        logger.info("Courses -> {} ", repository.findAll());
        logger.info("Count -> {} ", repository.count());
    }

    @Test
    public void sort() {
//        Sort sort = new Sort(Sort.Direction.ASC, List.of("name")); Line from course wrong
        logger.info("Sorted Courses -> {} ", repository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        //Courses -> [Course[JPA in 50 Steps], Course[Spring in 50 Steps], Course[Spring Boot in 100 Steps]]
    }

    @Test
    public void pagination() {
        PageRequest pageRequest = PageRequest.of(0, 3);

        Page<Course> firstPage = repository.findAll(pageRequest);
        logger.info("First Page -> {} ", firstPage);
        logger.info("First Page Content -> {} ", firstPage.getContent());

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(secondPageable);
        logger.info("Second Page -> {} ", secondPage);
        logger.info("Second Page Content -> {} ", secondPage.getContent());
    }

    @Test
    public void findUsingName() {
        logger.info("FindByName -> {} ", repository.findByName("JPA in 50 Steps"));
    }

    @Test
    public void findUsingStudentsName() {
        logger.info("findUsingStudentsName -> {} ", repository.findByName("Ranga"));
    }
}
