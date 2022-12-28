package com.in28Minutes.jpa.hibernate.demo;

import com.in28Minutes.jpa.hibernate.demo.entity.*;
import com.in28Minutes.jpa.hibernate.demo.repository.CourseRepository;
import com.in28Minutes.jpa.hibernate.demo.repository.EmployeeRepository;
import com.in28Minutes.jpa.hibernate.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(DemoApplication.class);
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /**** Testing relationships ****/
    /*
      logger.info("Course by id -> {}", courseRepository.findById(10001L));

        courseRepository.playWithEntityManager();

        studentRepository.saveStudentWithPassport();

        Review review = new Review(4, "Another review for JPA in 50 Steps");
        courseRepository.addReviewToCourse(10002L, review);
        List<Review> reviews = Arrays.asList(
                new Review(5, "Test review 5 rate"),
                new Review(4, "Test review 4 rate"),
                new Review(3, "Test review 3 rate")
        );
        courseRepository.addReviewsToCourse(10002L, reviews);

        studentRepository.insertStudentAndCourse(
                new Student("Jack"),
                new Course("Microservices in 100 Steps")
        );

        studentRepository.addCourseToStudent(20004L,
                new Course("Kotlin in 100 Steps"));


        courseRepository.addStudentToCourse(10004L, 20001L);
*/


        /**** Testing inheritance ****/
        // Jack FullTimeEmployee salary - 10000$
        // Jill PartTimeEmployee - 50$ per hour
       /* employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
        employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));

//        logger.info("All Employees -> {}", employeeRepository.findAll());
        logger.info("Full Time Employees -> {}",
                employeeRepository.retrieveAllFullTimeEmployees());

        logger.info("Part Time Employees -> {}",
                employeeRepository.retrieveAllPartTimeEmployees());
        */

        /**** ****/


    }
}
