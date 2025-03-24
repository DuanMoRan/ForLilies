package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Course;
import com.example.entity.ResponseData;
import com.example.entity.SelectCourse;
import com.example.entity.Student;
import com.example.tool.JwtUtil;

import com.example.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("space")
    public ResponseData findAll(@RequestBody Student student) {
        List<Student> students = service.find(student);
        if (students.isEmpty())
            return new ResponseData(false, students, "查询失败");
        else
            return new ResponseData(true, students, "查询成功");
    }

    @PostMapping("space/course")
    public ResponseData findCourse(@RequestBody Student student) {
        List<Course> courses = service.findCourses(student);
        if (courses.isEmpty())
            return new ResponseData(false, courses, "查询失败");
        else
            return new ResponseData(true, courses, "查询成功");
    }

    @PostMapping("add/course")
    public ResponseData addCourse(@RequestBody SelectCourse course) {
        if (service.addCourse(course))
            return new ResponseData(true, null, "添加成功");
        else
            return new ResponseData(false, null, "添加失败");
    }

    @PostMapping("dec/course")
    public ResponseData decCourse(@RequestBody SelectCourse course) {
        if (service.decCourse(course))
            return new ResponseData(true, null, "移除成功");
        else
            return new ResponseData(false, null, "移除失败");
    }

    @PostMapping("login")
    public ResponseData login(@RequestBody Student student) {
        System.out.println(student);
        if (service.login(student)) {
            String jwt = jwtUtil.generateToken(student.getStudentNo().toString());
            return new ResponseData(true, jwt, "登录成功");
        } else
            return new ResponseData(false, null, "登录失败");
    }

    @PutMapping("change/detail")
    public ResponseData changeDetail(@RequestBody Student student) {

        if (service.change(student))
            return new ResponseData(true, null, "修改成功");
        else
            return new ResponseData(false, null, "修改失败");
    }

    @PutMapping("register")
    public ResponseData register(@RequestBody Student student) {
        if (service.register(student))
            return new ResponseData(true, null, "注册成功");
        else
            return new ResponseData(false, null, "注册失败");
    }

}
