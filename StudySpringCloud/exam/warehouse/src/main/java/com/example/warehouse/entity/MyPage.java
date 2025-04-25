package com.example.warehouse.entity;

import lombok.Data;

@Data
public class MyPage {
    private Integer pages;
    private Integer size;

    public MyPage(Integer pages , Integer size){
        this.pages = pages;
        this.size = size;
    }
}
