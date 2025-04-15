package com.example.orders.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class Orders {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer productId;
    private Integer count;
    private String date;
}
