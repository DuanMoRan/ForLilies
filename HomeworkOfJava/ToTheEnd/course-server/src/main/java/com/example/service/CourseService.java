package com.example.service;

import java.util.List;

import com.example.entity.Course;

public interface CourseService {
    public List<Course> getAll();
    public List<Course> find(Course course);
    public Boolean insertCourse(Course course);
    public Boolean updateCourse(Course course);
    public Boolean deleteCourse(Course course);
}
