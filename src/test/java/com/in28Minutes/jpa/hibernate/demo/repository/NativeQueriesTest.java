package com.in28Minutes.jpa.hibernate.demo.repository;

import com.in28Minutes.jpa.hibernate.demo.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootTest
class NativeQueriesTest {

    @Autowired
    private EntityManager em;
    private Logger logger = LoggerFactory.getLogger(NativeQueriesTest.class);

    @Test
    void shouldReturnListOfCourses() {
        Query query = em.createNativeQuery("Select * from Course where is_deleted = 0", Course.class);
        List resultList = query.getResultList();
        logger.info("Select c from course -> {}", resultList);
    }

    @Test
    void shouldReturnListOfCoursesParameter() {
        Query query = em.createNativeQuery("Select * from Course where id = ? and is_deleted = false", Course.class);
        query.setParameter(1, 10001);
        List resultList = query.getResultList();
        logger.info("Select c from course -> {}", resultList);
    }

    void shouldReturnListOfCoursesNameParameter() {
        Query query = em.createNativeQuery("Select * from Course where id = ? and is_deleted = false", Course.class);
        query.setParameter("id", 10001);
        List resultList = query.getResultList();
        logger.info("Select c from course -> {}", resultList);
    }

    @Test
    @Transactional
    public void native_queries_to_update() {
        Query query = em.createNativeQuery("Update COURSE set last_updated_time=CURRENT_TIMESTAMP()");
        int noOfRowsUpdated = query.executeUpdate();
        logger.info("noOfRowsUpdated  -> {}", noOfRowsUpdated);
        //SELECT * FROM COURSE  -> [Course[Web Services in 100 Steps], Course[JPA in 50 Steps - Updated], Course[Spring in 50 Steps], Course[Spring Boot in 100 Steps]]
    }

}









