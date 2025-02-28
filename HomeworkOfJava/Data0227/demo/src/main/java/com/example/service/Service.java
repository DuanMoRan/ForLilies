package com.example.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson2.JSON;

import com.example.entity.Student;
import com.example.tool.Operator;

@WebServlet("/service/*")
public class Service extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String[] pathInfo = request.getPathInfo().split("/");
        switch (pathInfo[1]) {
            case "findAll": findAll(response); break;
            case "findByName": findByName(request, response); break;
            case "findById" : findById(request, response); break;
        
        }
    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException{
        String jsonBody = request.getReader().lines().collect(Collectors.joining());
        Student student = JSON.parseObject(jsonBody, Student.class);
        String sql = "insert into student(id , name , address , other) values(? , ?  , ? , ?)";
        System.out.println(Operator.update(sql, student.toParams()) > 0 ? "插入成功" : "插入失败");
    }

    @Override
    protected void doPut(HttpServletRequest request , HttpServletResponse response) throws IOException{
        String jsonBody = request.getReader().lines().collect(Collectors.joining());
        Student student = JSON.parseObject(jsonBody, Student.class);
        String sql = "update student set name = ? , address = ? , other = ? where id = ?";
        System.out.println(Operator.update(sql, new Object[] 
            {student.getName() , student.getAddress() , student.getOther() , student.getId()}) > 0 ?
            "插入成功" : "插入失败"
        );
    }

    @Override 
    protected void doDelete(HttpServletRequest request , HttpServletResponse response) throws IOException{
        String[] path = request.getPathInfo().split("/");
        String sql = "delete from student where id = ?" + path[2];
        response.getWriter().write(Operator.update(sql , null) > 0 ? "删除成功" : "删除失败");
    }

    private static void findAll(HttpServletResponse response) throws IOException{
        String sql = "select * from student";
        List<Student> students = Operator.query(Student.class, sql, null);
        response.getWriter().write(JSON.toJSONString(students));
    }

    private static void findByName(HttpServletRequest request , HttpServletResponse response) throws IOException{
        String[] path = request.getPathInfo().split("/");
        String sql = "select * from student where name like '%" + path[2] + "%'";
        List<Student> students = Operator.query(Student.class, sql, null);
        response.getWriter().write(JSON.toJSONString(students));
    }

    private static void findById(HttpServletRequest request , HttpServletResponse response) throws IOException{
        String sql = "select * from student where id = " + request.getPathInfo().split("/")[2];
        response.getWriter().write(JSON.toJSONString(Operator.query(Student.class, sql, null)));
    }
}
