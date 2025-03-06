package com.example.service;

import java.util.List;

import com.example.entity.Course;
import com.example.entity.Total;

public interface SelectService {
    Boolean save(Course course);
    List<Course> findAll(Total total);
    List<Course> findByDetail(Total total);
    Boolean deleteById(Course course);
    List<Course> findByKeywords(Total total);
}
