package com.in28Minutes.jpa.hibernate.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


//@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Min(1)
    @Max(5)
    @Getter
    @Setter
    private Integer rating;

    private String description;

    /** Many-To-One side fetch is eager by default */
    @ManyToOne
    @JsonIgnore
    @Getter
    @Setter
    private Course course;

    public Review(Integer rating, String description) {
        this.rating = rating;
        this.description = description;
    }
}
