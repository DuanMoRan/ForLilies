package com.example;

import java.util.Scanner;

import com.example.entity.User;
import com.example.service.Service;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String id , password;
        for(int i = 0 ; i < 5 ; i ++) {
            System.out.print("请输入账号");
            id = scan.nextLine();
            System.out.print("请输入密码");
            password = scan.nextLine();

            User user = Service.login(id, password);
            if(user != null) Service.service(scan, user);
        }

        scan.close();

    }
}