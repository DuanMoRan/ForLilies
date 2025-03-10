package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Course;
import com.example.entity.CourseDTO;
import com.example.entity.Message;
import com.example.entity.Page;
import com.example.entity.ResponseData;
import com.example.queues.SenderServer;
import com.example.service.CourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class CourseController {
    @Autowired
    private CourseService service;

    @Autowired
    private SenderServer server;

    @PostMapping("find/all")
    public ResponseData findAll(@RequestBody Page page) {
        PageHelper.startPage(page.getPageNo(), page.getSize());
        List<Course> courses = service.getAll();
        if (courses.isEmpty())
            return new ResponseData(false, new PageInfo<>(courses), "查询失败");
        else
            return new ResponseData(true, new PageInfo<>(courses), "查询成功");
    }

    @PostMapping("find")
    public ResponseData find(@RequestBody Course course) {
        List<Course> courses = service.find(course);
        if (courses.isEmpty())
            return new ResponseData(false, new PageInfo<>(courses), "查询失败");
        else
            return new ResponseData(true, new PageInfo<>(courses), "查询成功");
    }

    @PostMapping("create")
    public ResponseData createCourse(@RequestBody Course course) {
        if (service.insertCourse(course)) {
            send("c", course);
            return new ResponseData(true, null, "创建成功");
        } else
            return new ResponseData(false, null, "创建失败");
    }

    @PutMapping("update")
    public ResponseData updateCourse(@RequestBody Course course) {
        if (service.updateCourse(course)) {
            send("c", course);
            return new ResponseData(true, null, "更新成功");
        } else
            return new ResponseData(false, null, "更新失败");
    }

    @PostMapping("delete")
    public ResponseData deleteCourse(@RequestBody Course course) {
        if (service.deleteCourse(course)) {
            send("d", course);
            return new ResponseData(true, null, "删除成功");
        } else
            return new ResponseData(false, null, "删除失败");
    }

    public void send(String operator, Course course) {
        CourseDTO dto = new CourseDTO(course);
        Message message = new Message(operator, dto);
        server.sendMessage(message);
    }
}
