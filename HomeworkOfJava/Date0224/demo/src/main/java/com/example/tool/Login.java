package com.example.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Login {
    public static void login() {
        Scanner scan = new Scanner(System.in);

        System.out.print("请输入用户名：");
        String userName = scan.nextLine().trim();
        System.out.print("\n请输入密码");
        String password = scan.nextLine().trim();
        scan.close();
        String url = "jdbc:sqlserver://localhost:1433;databaseName=sbsmdx;encrypt=false";

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            // System.out.println("success for link");
            Operation.operation(conn);
        } catch (Exception e) {
            System.out.println("连接失败" + e.getMessage());
        } finally {
            
        }

    }
}
