package com.in28Minutes.jpa.hibernate.demo.repository;

import com.in28Minutes.jpa.hibernate.demo.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JPQLTest {

    @Autowired
    private EntityManager em;
    private Logger logger = LoggerFactory.getLogger(JPQLTest.class);

    @Test
    void shouldReturnListOfCourses() {
        Query query = em.createQuery("Select c from Course c");
        Query query2 = em.createNamedQuery("query_get_all");
        List resultList = query.getResultList();
        logger.info("Select c from course -> {}", resultList);
    }

    @Test
    void shouldReturnListOfCourses2() {
        TypedQuery<Course> typedQuery = em.createQuery("Select c from Course c", Course.class);
        TypedQuery<Course> typedQuery2 = em.createNamedQuery("query_get_all", Course.class);
        List<Course> resultList = typedQuery.getResultList();
        logger.info("Select c from course -> {}", resultList);
    }

    @Test
    void givenAnExistingIdShouldReturnACourse() {
        Long knownId = 10001L;
        TypedQuery<Course> typedQuery = em.createQuery("Select c from Course c where name like '%100 Steps' ", Course.class);

        List<Course> courses = typedQuery.getResultList();
        logger.info("Where query: {}", courses);
    }


}









