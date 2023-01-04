package com.in28Minutes.jpa.hibernate.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
@Cacheable  // Course details will be cached in 2nd level cache for further requests
@SQLDelete(sql = "update course set is_deleted=true where id =?")   // for soft deletion
@Where(clause = "is_deleted = false")   // select taking into account soft deletion column is_deleted (apply this for querying course, doesn't work with native queries)
public class Course {

    private static Logger LOGGER = LoggerFactory.getLogger(Course.class);

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Getter
    private Long id;

    @Column(name = "name", nullable = false)
    @Getter
    @Setter
    private String name;

    private boolean isDeleted;

    @CreationTimestamp
    @Getter
    @Setter
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Getter
    @Setter
    private LocalDateTime lastUpdatedTime;


    /** One-To-Many side fetch strategy by default is lazy */
    @OneToMany( mappedBy = "course")
    @Setter(AccessLevel.NONE)
    @Getter
    private Set<Review> reviews = new HashSet<>();



    /** Many-To-many by default uses lazy fetch */
    @ManyToMany(mappedBy = "courses")
    @Setter(AccessLevel.NONE)
    @Getter
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

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

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    @PreRemove  // Update the entity in the context, so hibernate can know the entity has been updated
    public void preRemoved() {
        LOGGER.info("Setting is_deleted to true");
        this.isDeleted = true;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", lastUpdatedTime=" + lastUpdatedTime +
                '}';
    }
}
