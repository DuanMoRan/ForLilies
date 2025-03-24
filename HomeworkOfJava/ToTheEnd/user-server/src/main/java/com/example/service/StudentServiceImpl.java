package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.aop.CreateCourse;
import com.example.aop.DeleteCourse;
import com.example.dao.StudentMapper;
import com.example.entity.Course;
import com.example.entity.SelectCourse;
import com.example.entity.Student;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentMapper mapper;

    @Override
    public List<Student> find(Student student){
        return mapper.getStudents(student);
    }

    @Override
    public List<Course> findCourses(Student student){
        return mapper.getCourses(student);
    }

    @Override
    public Boolean login(Student student){
        return !mapper.getStudents(student).isEmpty();
    }

    @Override
    public Boolean change(Student student){
        return mapper.change(student) > 0;
 
    }

    @Override
    @CreateCourse
    public Boolean addCourse(SelectCourse course){
        if(mapper.findCourse(course).getRemainder() == 0) return false;
        else mapper.addCourse(course);
        return true;
    }

    @Override
    @DeleteCourse
    public Boolean decCourse(SelectCourse course){
        return mapper.dec(course) > 0;
    }

    @Override
    public Boolean register(Student student){
        return mapper.insertStudent(student) > 0 ;
    }
}
