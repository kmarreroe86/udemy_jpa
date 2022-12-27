package com.in28Minutes.jpa.hibernate.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    /** Student is the owner of the relationship, so it will have passport_id column */
    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;



    /** Student is the owner of the relationship, either way is good */
    /** Many-To-many by default uses lazy fetch */
    @ManyToMany
    // joinColumn -> student_id
    // inverseJoinColumn -> course_id
    @JoinTable(name = "STUDENT_COURSE",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID")
    )
    @Setter(AccessLevel.NONE)
    @Getter
    private Set<Course> courses = new HashSet<>();

    public Student(String name) {
        this.name = name;
        this.id = null;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }
}
