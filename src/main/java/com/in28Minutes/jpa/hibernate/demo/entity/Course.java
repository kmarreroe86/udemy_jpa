package com.in28Minutes.jpa.hibernate.demo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQueries(
        value = {
                @NamedQuery(name = "query_get_all", query = "Select c from Course c"),
                @NamedQuery(name = "query_get_100_Step_courses", query = "Select c from Course c where name like '%100 Steps'")
        }
)
public class Course {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Getter
    private Long id;

    @Column(name = "name", nullable = false)
    @Getter
    @Setter
    private String name;

    @CreationTimestamp
    @Getter
    @Setter
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Getter
    @Setter
    private LocalDateTime lastUpdatedTime;

    @OneToMany( mappedBy = "course")
    @Setter(AccessLevel.NONE)
    @Getter
    private Set<Review> reviews = new HashSet<>();

    public Course(String name) {
        this.id = null;
        this.name = name;
    }

    public boolean addReview(Review review) {
        return this.reviews.add(review);
    }

    public boolean removeReview(Review review) {
        return this.reviews.remove(review);
    }
}
