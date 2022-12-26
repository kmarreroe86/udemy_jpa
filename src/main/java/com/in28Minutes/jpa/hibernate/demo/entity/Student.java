package com.in28Minutes.jpa.hibernate.demo.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String name;

    /** In one-to-one relations are fetched eagerly by default, set Lazy to change it. */
    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    public Student(String name) {
        this.name = name;
        this.id = null;
    }
}
