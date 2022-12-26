package com.in28Minutes.jpa.hibernate.demo.repository;

import com.in28Minutes.jpa.hibernate.demo.entity.Passport;
import com.in28Minutes.jpa.hibernate.demo.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {

    @Autowired
    private EntityManager em;

    private Logger logger = LoggerFactory.getLogger(StudentRepository.class);


    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public void deleteById(Long id) {
        var Student = findById(id);
        em.remove(Student);
    }

    public Student save(Student model) {

        if (model.getId() == null) {
            em.persist(model);
        } else {
            em.merge(model);
        }
        return model;
    }

    public Student saveStudentWithPassport() {
        var passport = new Passport("HN3185");
        em.persist(passport);

        var student = new Student("Mike");
        student.setPassport(passport);

        em.persist(student);

        return student;
    }
}


























