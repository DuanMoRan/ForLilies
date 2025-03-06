package com.example.entity;

import lombok.Data;

@Data
public class ResponseData {
    private Boolean flag;
    private Object object;
    private String message;


    public ResponseData(Boolean flag , Object object , String message){
        this.flag = flag;
        this.object = object;
        this.message = message;
    }
}
