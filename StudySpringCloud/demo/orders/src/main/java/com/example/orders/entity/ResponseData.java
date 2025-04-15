package com.example.orders.entity;

import lombok.Data;

@Data
public class ResponseData {
    private Boolean flag;
    private Object data;
    private String message;
}
