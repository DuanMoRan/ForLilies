package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.Course;

@Mapper
public interface CourseMapper {
    List<Course> getAll(Course course);
    Integer createCourse(Course course);
    Integer updateCourse(Course course);
    Integer deleteCourse(Course course);
    Integer add(Course course);
    Integer decreas(Course course);
}
