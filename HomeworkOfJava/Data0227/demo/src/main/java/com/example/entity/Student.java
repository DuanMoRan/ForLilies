package com.example.entity;

import lombok.Data;

@Data
public class Student {
    private String id;
    private String name;
    private String address;
    private String other;

    public Object[] toParams(){
        return new Object[] {
            this.id,
            this.name,
            this.address,
            this.other
        };
    }
}
