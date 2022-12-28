package com.in28Minutes.jpa.hibernate.demo.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

@MappedSuperclass   // Remove the inheritance, abstract Employee acts like a common place for properties. PartTimeEmployee and FullTimeEmployee tables will be created
//@Entity // Commented due @MappedSuperclass annotation was aggregated

//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // InheritanceType.SINGLE_TABLE is the default strategy
//@DiscriminatorColumn(name = "EmployeeType")

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)    // Each concrete class will have its own table

//@Inheritance(strategy = InheritanceType.JOINED) // All classes including abstract Employee(with commons attributes) will have its table. Data is retrieved using joins
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
