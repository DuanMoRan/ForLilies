package com.example.entity;

import lombok.Data;

@Data
public class Pet {
    private String id;
    private String idOfMaster;
    private String name;
    private String type;
    private Integer age;
    private String gender;
    private Integer price;

    @Override
    public String toString() {
        return String.format(
            "宠物详情: ID: %-6s 主人ID: %-6s 名字: %-6s 品种: %-6s 年龄: %-6d 岁 性别: %-6s 价格: %-6d元",
            id, idOfMaster, name, type, age, gender,price
        );
    }
}
