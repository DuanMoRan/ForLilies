package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Course;
import com.example.entity.ResponseData;
import com.example.entity.Total;
import com.example.service.SelectService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.DeleteMapping;



@RestController
public class SelectController {
    @Autowired
    private SelectService service;

    // @PostMapping("save")
    // public ResponseData save(@RequestBody Course course) {
    //     if(service.save(course)) return new ResponseData(true, null, "添加成功");
    //     else return new ResponseData(false, null, "添加失败");
        
    // }

    @PostMapping("find/all")
    public ResponseData findAll(@RequestBody Total total) {
        List<Course> courses = service.findAll(total);
        if(courses.isEmpty()) return new ResponseData(false, courses, "查询失败");
        else return new ResponseData(true, courses, "查询成功");
    }
    
    @PostMapping("find/detail")
    public ResponseData findByDetail(@RequestBody Total total) {
        List<Course> courses = service.findByDetail(total);
        if(courses.isEmpty()) return new ResponseData(false, courses, "查询失败");
        else return new ResponseData(true, courses, "查询成功");
    }

    // @DeleteMapping("delete")
    // public ResponseData deleteById(@RequestBody Course course){
    //     if(service.deleteById(course)) return new ResponseData(true, null, "删除成功");
    //     else return new ResponseData(false, null, "删除失败");
    // }

    @PostMapping("find/keywords")
    public ResponseData findByKeywords(@RequestBody Total total){
        List<Course> courses = service.findByKeywords(total);
        if(courses.isEmpty()) return new ResponseData(false, courses, "查询失败");
        else return new ResponseData(true, courses, "查询成功");
    }
    
}
