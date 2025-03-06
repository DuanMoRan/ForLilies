package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CourseMapper;
import com.example.entity.Course;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseMapper mapper;

    @Override
    public List<Course> getAll(){
        return mapper.getAll(null);
    }

    @Override
    public List<Course> find(Course course){
        return mapper.getAll(course);
    }

    @Override
    public Boolean insertCourse(Course course) {
        return mapper.createCourse(course) > 0;
    }

    @Override
    public Boolean updateCourse(Course course) {
        return mapper.updateCourse(course) > 0;
    }

    @Override
    public Boolean deleteCourse(Course course) {
        return mapper.deleteCourse(course) > 0;
    }
}
