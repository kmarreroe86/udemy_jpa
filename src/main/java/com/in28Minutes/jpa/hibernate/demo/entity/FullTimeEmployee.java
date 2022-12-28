package com.in28Minutes.jpa.hibernate.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Data
public class FullTimeEmployee extends Employee {

    private BigDecimal salary;

    public FullTimeEmployee(String name, BigDecimal salary) {
        super(name);
        this.salary = salary;
    }

    protected FullTimeEmployee() {
    }
}
