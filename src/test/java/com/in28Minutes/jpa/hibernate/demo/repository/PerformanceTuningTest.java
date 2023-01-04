package com.in28Minutes.jpa.hibernate.demo.repository;

import com.in28Minutes.jpa.hibernate.demo.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import java.util.List;

@SpringBootTest
public class PerformanceTuningTest {

    private Logger logger = LoggerFactory.getLogger(PerformanceTuningTest.class);

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void creatingNPlusOneProblem() {
        List<Course> courses = em
                .createNamedQuery("query_get_all", Course.class)
                .getResultList();
        for (Course course : courses) {
            logger.info("Course -> {} Students -> {}", course, course.getStudents());
        }
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_EntityGraph() {

        EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        Subgraph<Object> subGraph = entityGraph.addSubgraph("students");

        List<Course> courses = em
                .createNamedQuery("query_get_all", Course.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();

        for (Course course : courses) {
            logger.info("Course -> {} Students -> {}", course, course.getStudents());
        }
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_JoinFetch() {
        List<Course> courses = em
                .createNamedQuery("query_get_all_courses_join_fetch", Course.class)
                .getResultList();
        for (Course course : courses) {
            logger.info("Course -> {} Students -> {}", course, course.getStudents());
        }
    }
}
