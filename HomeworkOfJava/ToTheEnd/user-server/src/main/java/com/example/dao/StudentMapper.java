package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.Course;
import com.example.entity.SelectCourse;
import com.example.entity.Student;

@Mapper
public interface StudentMapper {
    public List<Student> getStudents(Student student);
    public List<Course> getCourses(Student student);
    public Integer insertStudent(Student student);
    public Integer change(Student student);
    public Course findCourse(SelectCourse course);
    public Integer addCourse(SelectCourse course);
    public Integer dec(SelectCourse course);
}