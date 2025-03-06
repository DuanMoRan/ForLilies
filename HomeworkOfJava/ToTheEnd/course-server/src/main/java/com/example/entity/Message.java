package com.example.entity;

import lombok.Data;

@Data
public class Message {
    private String operator;
    private Object data;

    public Message(String operator , CourseDTO dto){
        this.operator = operator;
        this.data = dto;
    }
}
