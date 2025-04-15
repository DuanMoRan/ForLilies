package com.example.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class Goods {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Double price;
    private String type;
}
