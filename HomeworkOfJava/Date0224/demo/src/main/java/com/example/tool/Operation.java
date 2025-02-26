package com.example.tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Operation {
    static String url;

    public static void operation(Connection conn) throws SQLException {
        System.out.println("按下相应数字键进行操作");
        System.out.println("1.显示所有学生");
        System.out.println("2.添加学生");
        System.out.println("3.删除学生");
        System.out.println("4.更新学生");

        try (Scanner scan = new Scanner(System.in)) {
            switch (scan.nextInt()) {
                case 1:
                    showAll(conn);
                    break;

                case 2:
                    insert(conn, scan);
                    break;

                case 3:
                    detele(conn, scan);
                    break;

                case 4:
                    update(conn, scan);
                    break;

                default:
                    scan.close();
                    return;
            }
        }
    }

    public static void showAll(Connection conn) throws SQLException {
        url = "select * from student";
        PreparedStatement statement = conn.prepareStatement(url);
        statement.executeQuery();
        statement.close();
    }

    public static void insert(Connection conn, Scanner scan) {
        url = "insert into student(name , age , gender , cellphone , address) values(? , ?  , ? , ? , ?)";
        try (PreparedStatement statement = conn.prepareStatement(url)) {
            System.out.print("请输入姓名");
            statement.setString(1, scan.nextLine());
            System.out.print("请输入年龄");
            statement.setInt(2, Integer.parseInt(scan.nextLine()));
            System.out.print("请输入性别");
            statement.setString(3, scan.nextLine());
            System.out.print("请输入手机号");
            statement.setString(4, scan.nextLine());
            System.out.print("请输入地址");
            statement.setString(5, scan.nextLine());

            System.out.println(statement.executeUpdate() > 0 ? "成功插入"  : "插入失败");
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }

    }

    public static void detele(Connection conn, Scanner scan) {
        url = "delete from student where sno = ?";
        try (PreparedStatement statement = conn.prepareStatement(url)) {

            System.out.print("请输入学号");
            statement.setInt(1, Integer.parseInt(scan.nextLine()));

            System.out.println(statement.executeUpdate() > 0 ? "成功删除"  : "删除失败");

            statement.close();
        } catch (SQLException e) {
            e.getErrorCode();
        }
    }

    public static void update(Connection conn, Scanner scan) {
        url = "update student set age = ? , cellphone = ? , address = ? where sno = ?";
        try (PreparedStatement statement = conn.prepareStatement(url)) {
            System.out.print("请输入学号");
            statement.setInt(4, Integer.parseInt(scan.nextLine()));
            System.out.print("请输入更新后的年龄");
            statement.setInt(1, Integer.parseInt(scan.nextLine()));
            System.out.print("请输入更新后的手机号");
            statement.setString(2, scan.nextLine());
            System.out.print("请输入更新后的地址");
            statement.setString(3, scan.nextLine());

            System.out.println(statement.executeUpdate() > 0 ? "更新插入"  : "更新失败");

            statement.clearParameters();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());

        }
    }
}
