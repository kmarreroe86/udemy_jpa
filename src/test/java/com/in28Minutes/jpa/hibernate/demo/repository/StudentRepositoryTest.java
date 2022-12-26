package com.in28Minutes.jpa.hibernate.demo.repository;

import com.in28Minutes.jpa.hibernate.demo.DemoApplication;
import com.in28Minutes.jpa.hibernate.demo.entity.Passport;
import com.in28Minutes.jpa.hibernate.demo.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
class StudentRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(StudentRepositoryTest.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    EntityManager em;



    /** As Student - Passport one-to-one is lazy, @Transactional its need it to be able to get data in student.getPassport()
     * it will throw error as Hibernate session ends with em.find(Student.class, 20001L); otherwise
     * */
    @Test
    @Transactional
    void givenExistingIdShouldReturnPassport() {

        Student student = em.find(Student.class, 20001L);
        logger.info("Student -> {}", student);
        logger.info("Passport -> {}", student.getPassport());
    }

    @Test
    @Transactional
    void givenExistingIdShouldReturnStudent() {

        Passport passport = em.find(Passport.class, 40001L);
        logger.info("Passport -> {}", passport);
        logger.info("Student -> {}", passport.getStudent());
    }
}
