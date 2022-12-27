package com.in28Minutes.jpa.hibernate.demo.repository;

import com.in28Minutes.jpa.hibernate.demo.entity.Course;
import com.in28Minutes.jpa.hibernate.demo.entity.Review;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    EntityManager em;

    private Logger logger = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Test
    void givenAnExistingIdShouldReturnACourse() {
        Long knownId = 10001L;

        var course = courseRepository.findById(knownId);

        assertNotNull(course);
        assertEquals(course.getName(), "JPA in 50 Steps");
    }

    @Test
    @DirtiesContext
    void givenAnExistingIdShouldDeleteACourse() {
        Long knownId = 10002L;

        courseRepository.deleteById(knownId);

        var courseAfterDeletion = courseRepository.findById(knownId);
        assertNull(courseAfterDeletion);
    }

    @Test
    void givenNullIdShouldCreateACourse() {
        var course = new Course();
        course.setName("Java Streams");
        assertNull(course.getId());

        Course persistedCourse = courseRepository.save(course);

        assertNotNull(persistedCourse.getId());
    }

    @Test
    void givenExistingIdIdShouldUpdateACourse() {

        Long existingId = 10002L;
        var existingCourse = courseRepository.findById(existingId);
        var previousExistingCourseName = existingCourse.getName();
        existingCourse.setName(previousExistingCourseName + "_Edited");

        existingCourse = courseRepository.save(existingCourse);

        assertEquals(existingId, existingCourse.getId());
        assertEquals(previousExistingCourseName + "_Edited", existingCourse.getName());
    }

    @Test
    void testPlayWithEntityManager() {
        courseRepository.playWithEntityManager();
    }

    @Test
    @Transactional
    void testReviewsForCourse() {
        Course course = courseRepository.findById(10001L);
        logger.info("Reviews: {}", course.getReviews());
    }

    @Test
    @Transactional
    void retrieveReviewsForCourse() {
        Course course = courseRepository.findById(10001L);
        logger.info("{}", course.getReviews());
    }

    @Test
    @Transactional
    void retrieveCourseForReview() {
        Review review = em.find(Review.class, 50001L);
        logger.info("Review course: {}", review.getCourse());
    }
}









