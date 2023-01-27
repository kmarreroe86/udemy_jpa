package com.in28Minutes.jpa.hibernate.demo.controller;

import com.in28Minutes.jpa.hibernate.demo.repository.CourseRepository;
import com.in28Minutes.jpa.hibernate.demo.repository.CourseSpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class TestController {

    @Autowired
    private CsvFileGenerator csvGenerator;

    @Autowired
    private CourseSpringDataRepository courseRepository;

    @GetMapping("/export-to-csv")
    public void exportIntoCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=\"courses.csv\"");
        csvGenerator.writeStudentsToCsv(courseRepository.findAll(), response.getWriter());
    }
}
