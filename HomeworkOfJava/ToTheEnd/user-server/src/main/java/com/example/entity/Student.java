package com.example.entity;

import lombok.Data;

@Data
public class Student {
    private Integer studentNo;
    private String loginPwd;
    private String studentName;
    private String sex;
    private String phone;
    private String address;
    private Integer age;
}
