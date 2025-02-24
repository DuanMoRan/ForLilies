package com.example;

import java.util.Scanner;

import com.example.tool.Login;

public class Main {
    public static void main(String[] args) {
        System.out.println("请按下相应数字键");
        System.out.println("1.登录");
        System.out.println("2.退出");
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        scan.close();
        if(a == 1) Login.login();
        else return;
    }
}