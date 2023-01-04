package com.in28Minutes.jpa.hibernate.demo.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String line1;

    private String line2;

    private String city;
}
