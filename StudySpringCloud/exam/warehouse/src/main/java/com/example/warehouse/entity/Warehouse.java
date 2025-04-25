package com.example.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class Warehouse {
    @TableId()
    private Integer id;
    private String name;
    private String address;
    private Integer number;
}
