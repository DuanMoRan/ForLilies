package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Data
@Document(indexName = "course")
public class Course {
    @Id
    private Long courseNo;
    private String courseName;
    private String detail;
}
