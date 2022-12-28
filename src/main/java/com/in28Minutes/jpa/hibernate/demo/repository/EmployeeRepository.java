package com.in28Minutes.jpa.hibernate.demo.repository;

import com.in28Minutes.jpa.hibernate.demo.entity.Employee;
import com.in28Minutes.jpa.hibernate.demo.entity.FullTimeEmployee;
import com.in28Minutes.jpa.hibernate.demo.entity.PartTimeEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    @Autowired
    private EntityManager em;

    private Logger logger = LoggerFactory.getLogger(EmployeeRepository.class);


    public Employee findById(Long id) {
        return em.find(Employee.class, id);
    }

    public void insert(Employee employee) {
        em.persist(employee);
    }

    public List<Employee> findAll() {
        return em.createQuery("select e from Employee e", Employee.class)
                .getResultList();
    }

    public List<PartTimeEmployee> retrieveAllPartTimeEmployees() {
        return em.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
    }

    public List<FullTimeEmployee> retrieveAllFullTimeEmployees() {
        return em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
    }
}






























