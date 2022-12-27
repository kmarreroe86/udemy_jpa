package com.in28Minutes.jpa.hibernate.demo.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class Employee {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Getter
    private Long id;

    @Column(nullable = false)
    private String name;

    public Employee(String name){
        this.name = name;
    }

}
