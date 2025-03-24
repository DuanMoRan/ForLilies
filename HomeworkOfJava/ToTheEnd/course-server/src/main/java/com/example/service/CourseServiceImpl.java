package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.aop.CreateCourse;
import com.example.aop.DeleteCourse;
import com.example.dao.CourseMapper;
import com.example.entity.Course;

@Service
@Transactional
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
    @CreateCourse
    public Boolean insertCourse(Course course) {
        return mapper.createCourse(course) > 0;
    }

    @Override
    @CreateCourse
    public Boolean updateCourse(Course course) {
        return mapper.updateCourse(course) > 0;
    }

    @Override
    @DeleteCourse
    public Boolean deleteCourse(Course course) {
        return mapper.deleteCourse(course) > 0;
    }
}
