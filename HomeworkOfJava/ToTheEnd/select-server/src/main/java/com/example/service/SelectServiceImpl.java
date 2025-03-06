package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.entity.Course;
import com.example.entity.Total;


@Service
public class SelectServiceImpl implements SelectService{

    @Autowired
    private SelectRepository repository;

    @Override
    public Boolean save(Course course) {
        try {
            repository.save(course);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Course> findAll(Total total){
        Pageable pageable = PageRequest.of(total.getPage().getPageNo() - 1, total.getPage().getSize());
        List <Course> courses = new ArrayList<>();
        repository.findAll(pageable).forEach(courses::add);
        return courses;
    }

    @Override
    public List<Course> findByDetail(Total total){
        Pageable pageable = PageRequest.of(total.getPage().getPageNo() - 1, total.getPage().getSize());
        return repository.findByDetail(total.getCourse().getDetail() , pageable);
    }
    
    @Override
    public Boolean deleteById(Course course){
        if(repository.existsById(course.getCourseNo())){
            repository.deleteById(course.getCourseNo());
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Course> findByKeywords(Total total){
        Pageable pageable = PageRequest.of(total.getPage().getPageNo() - 1, total.getPage().getSize());
        return repository.findByKeywords(total.getCourse().getDetail(), pageable);
    }
}
