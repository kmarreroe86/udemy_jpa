package com.in28Minutes.jpa.hibernate.demo.repository;

import com.in28Minutes.jpa.hibernate.demo.entity.Course;
import com.in28Minutes.jpa.hibernate.demo.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    @Autowired
    private EntityManager em;

    private Logger logger = LoggerFactory.getLogger(CourseRepository.class);


    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public void deleteById(Long id) {
        var course = findById(id);
        em.remove(course);
    }

    public Course save(Course model) {

        if (model.getId() == null) {
            em.persist(model);
        } else {
            em.merge(model);
        }
        return model;
    }

    /**
     * As we are inside a transactional method when course is saved is managed by the entity manager.
     * Any change made to this entity will be persisted without doing explicitly */
    public void playWithEntityManager() {
        logger.info("playWithEntityManager - start");
        var course = new Course("Web Services in 100 steps");
        em.persist(course);
        var course2 = new Course("Angular in 100 Steps");
        em.persist(course2);
        em.flush();              // Synchronize the persistence context to the underlying database.(Persist in Db)

//        em.clear();  // Clear the persistence context, causing all managed entities to become detached.

        course.setName("Web Services in 100 steps - Updated");
        course2.setName("Angular in 100 Steps - Update");

        em.refresh(course);     // Refresh the state of the instance from the database, overwriting changes made to the entity, if any.

        em.detach(course2);      // Remove the given entity from the persistence context

        em.flush();
    }

    public void addReviewToCourse(Long courseId, Review review) {

        Course course = findById(courseId);
        if (course == null) { return; }

        logger.info("Course {} reviews: {}", course, course.getReviews());

        // Setting the relationship course <-> review
        course.addReview(review);
        review.setCourse(course);

        // Persist review
        em.persist(review);
    }

    public void addReviewsToCourse(Long courseId, List<Review> reviews) {

        Course course = findById(courseId);
        if (course == null) { return; }

        // Setting the relationship course <-> review
        reviews.forEach(rev -> {
            course.addReview(rev);
            rev.setCourse(course);
            em.persist(rev);
        });


    }
}


























