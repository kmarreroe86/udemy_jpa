package com.in28Minutes.jpa.hibernate.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Data
public class PartTimeEmployee extends Employee {

    private BigDecimal hourlyWage;

    public PartTimeEmployee(String name, BigDecimal hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }

    protected PartTimeEmployee() {  }
}
