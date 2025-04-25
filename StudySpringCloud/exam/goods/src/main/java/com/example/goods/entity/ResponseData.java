package com.example.goods.entity;

import lombok.Data;

@Data
public class ResponseData {
    private Boolean flag;
    private Object data;
    private String message;
}
