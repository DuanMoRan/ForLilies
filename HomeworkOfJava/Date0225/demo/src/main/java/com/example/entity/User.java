package com.example.entity;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private String password;
    private Integer point;
    private String cellphton;
    private Integer idAdministrator;
}
