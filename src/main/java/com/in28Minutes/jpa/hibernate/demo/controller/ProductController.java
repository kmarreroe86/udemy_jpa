package com.in28Minutes.jpa.hibernate.demo.controller;

import com.in28Minutes.jpa.hibernate.demo.dto.ProductAbstraction;
import com.in28Minutes.jpa.hibernate.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/products")
    public List<ProductAbstraction> getAllProducts() {
        return productRepository.findAllAbstraction();
    }
}
