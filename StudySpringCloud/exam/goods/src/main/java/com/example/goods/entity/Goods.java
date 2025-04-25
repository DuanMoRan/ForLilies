package com.example.goods.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class Goods {
    @TableId()
    private Integer id;
    private String name;
    private Double price;
    private Double weight;
    private Integer idWarehouse;
    private String type;
}
