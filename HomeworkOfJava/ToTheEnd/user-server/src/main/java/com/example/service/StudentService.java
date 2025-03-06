package com.example.service;

import java.util.List;

import com.example.entity.Course;
import com.example.entity.SelectCourse;
import com.example.entity.Student;

public interface StudentService {
    List<Student> find(Student student);
    List<Course> findCourses(Student student);
    Boolean login(Student student);
    Boolean change(Student student);
    Boolean addCourse(SelectCourse course);
    Boolean decCourse(SelectCourse course);
    Boolean register(Student student);
}
