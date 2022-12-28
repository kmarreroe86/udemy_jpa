package com.in28Minutes.jpa.hibernate.demo.repository;

import com.in28Minutes.jpa.hibernate.demo.entity.Course;
import com.in28Minutes.jpa.hibernate.demo.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

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
    void givenAnExistingCourseIdShouldReturnACourse() {
        Long knownId = 10001L;
        TypedQuery<Course> typedQuery = em.createQuery("Select c from Course c where name like '%100 Steps' ", Course.class);

        List<Course> courses = typedQuery.getResultList();
        logger.info("Where query: {}", courses);
    }

    @Test
    void jpqlCoursesWithoutStudents() {
        /* Query is written using entities and fields names, not tables */
        TypedQuery<Course> typedQuery = em.createQuery("Select c from Course c where c.students is empty", Course.class);

        List<Course> courses = typedQuery.getResultList();
        logger.info("Courses without students: {}", courses);
    }

    @Test
    void jpqlCoursesWithAtLeas2Students() {
        TypedQuery<Course> typedQuery = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);

        List<Course> courses = typedQuery.getResultList();
        logger.info("Courses with at least 2 students: {}", courses);
    }

    @Test
    void jpqlCoursesNameWithAtLeas2Students() {
        TypedQuery<String> typedQuery = em.createQuery("Select c.name from Course c where size(c.students) >= 2", String.class);

        List<String> courses = typedQuery.getResultList();
        logger.info("Courses with at least 2 students: {}", courses);
    }

    @Test
    void jpqlCoursesOrderedAscByNumberOfStudents() {
        TypedQuery<String> typedQuery = em.createQuery("Select c.name from Course c order by size(c.students)", String.class);

        List<String> courses = typedQuery.getResultList();
        logger.info("Courses ordered asc by number of students: {}", courses);
    }

    @Test
    void jpqlCoursesOrderedDescByNumberOfStudents() {
        TypedQuery<String> typedQuery = em.createQuery("Select c.name from Course c order by size(c.students) desc", String.class);

        List<String> courses = typedQuery.getResultList();
        logger.info("Courses ordered desc by number of students: {}", courses);
    }

    @Test
    void studentsWithPassportWithCertainPattern() {
        var pattern = "%1234%";
        TypedQuery<String> studentTypedQuery = em.createQuery("Select e.name from Student e where e.passport.number like ?1", String.class);
        studentTypedQuery.setParameter(1, pattern);

        List<String> students = studentTypedQuery.getResultList();
        logger.info("Students with pattern {}: {}", pattern, students);
    }

    //JOIN => Select c, s from Course c JOIN c.students s
    //LEFT JOIN => Select c, s from Course c LEFT JOIN c.students s
    //CROSS JOIN => Select c, s from Course c, Student s
    //3 and 4 =>3 * 4 = 12 Rows
    @Test
    @Transactional
    public void join(){
        Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());
        for(Object[] result:resultList){
            logger.info("Course: {} Student: {}", result[0], result[1]);
        }
    }

    @Test
    @Transactional
    public void left_join(){
        Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());
        for(Object[] result:resultList){
            logger.info("Course: {} Student: {}", result[0], result[1]);
        }
    }

    @Test
    @Transactional
    public void cross_join(){
        Query query = em.createQuery("Select c, s from Course c, Student s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());
        for(Object[] result:resultList){
            logger.info("Course: {} Student: {}", result[0], result[1]);
        }
    }


}









