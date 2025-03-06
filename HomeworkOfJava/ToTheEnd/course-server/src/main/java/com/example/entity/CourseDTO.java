package com.example.entity;

import lombok.Data;

@Data
public class CourseDTO {
    private Integer courseNo;
    private String courseName;
    private String detail;

    public CourseDTO(Course course){
        this.courseNo = course.getCourseNo();
        this.courseName = course.getCourseName();
        this.detail = course.getDetail();
    }
}
