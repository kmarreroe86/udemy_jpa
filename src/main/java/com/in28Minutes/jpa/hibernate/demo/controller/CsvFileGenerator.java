package com.in28Minutes.jpa.hibernate.demo.controller;

import com.in28Minutes.jpa.hibernate.demo.entity.Course;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CsvFileGenerator {

    public void writeStudentsToCsv(List<Course> students, Writer writer) {
        try {

            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            for (Course course : students) {
                printer.printRecord(course.getId(), course.getName(), course.getReviews(), course.getCreatedDate());
            }
            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
