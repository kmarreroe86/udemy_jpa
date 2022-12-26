package com.in28Minutes.jpa.hibernate.demo.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Passport {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String number;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
    @ToString.Exclude
    private Student student;

    public Passport(String number) {
        this.id = null;
        this.number = number;
    }
}
