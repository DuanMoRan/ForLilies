package com.example.service;

import java.util.List;
import java.util.Scanner;

import com.example.entity.Pet;
import com.example.entity.User;
import com.example.tool.Operator;

public class Service {
    public static User login(String id, String password) {
        String sql = "select * from user where id = ? and password = ?";

        List<User> a = Operator.query(User.class, sql, new String[] { id, password });
        if (!a.isEmpty()) return a.get(0);
        else System.out.println("账号或密码错误");

        return null;
    }

    public static void service(Scanner scan, User user) {
        while (true) {
            System.out.println("输入相应数字以执行操作");
            System.out.println("0.退出");
            System.out.println("1.查看所有宠物");
            System.out.println("2.购买宠物");
            if (user.getIdAdministrator() == 1) {
                System.out.println("3.添加新的宠物");
                System.out.println("4.删除宠物");
                System.out.println("5.更改宠物信息");
            }

            switch (scan.nextInt()) {
                case 0: return;
                case 1: find(scan);  break;
                case 2: buy(scan, user); break;
                case 3: insert(scan, user); break;
                case 4:
            }
        }
    }

    public static void find(Scanner scan){
        System.out.println("1.按价格排序");
        System.out.println("2.按年龄排序");

        String sql = "select * from pet order by ";
        if(scan.nextInt() == 1) sql += "price";
        else sql += "age";

        List<Pet> pets = Operator.query(Pet.class, sql, null);

        for(Pet pet : pets){
            System.out.println(pet.toString());
        }
    }

    public static void buy(Scanner scan , User user){
        System.out.println("请输入购买宠物的ID");
        String id = scan.nextLine();
        String sql = "update pet set idOfMaster = ? where id = ?";
        if(Operator.update(sql,  new String[] {user.getId() , id}) > 0){        
            sql = "update user set point = point - (select price from pet where id = ?) where id = ?";
            Operator.update(sql, new String[] {id , user.getId()});
        }else System.out.println("该宠物ID不存在");
    }

    public static void insert(Scanner scan , User user){
        if(user.getIdAdministrator() == 0) return ;
        System.out.print("新的宠物的ID为：");
        String id = scan.nextLine();
        System.out.print("新的宠物的名字为：");
        String name = scan.nextLine();
        System.out.print("新的宠物的品种为：");
        String type = scan.nextLine();
        System.out.print("新的宠物的年龄为：");
        Integer age = scan.nextInt();
        System.out.println("新的宠物的性别为：");
        String gender = scan.nextLine();
        System.out.println("新的宠物的价格为：");
        Integer price = scan.nextInt();

        String sql = "insert into pet(id , name , type , age , gender , price) values(? , ? , ? , ? , ? , ?)";
        
        System.out.println(
            Operator.update(sql, new Object[] {id , name , type , age , gender , price}) > 0 ?
            "插入成功" : "插入失败"
        );
    }

    public static void delete(Scanner scan , User user){
        if(user.getIdAdministrator() == 0) return;

        System.out.println("请输入需要删除的宠物ID");
        String id = scan.nextLine();

        String sql = "delete from pet where id = ?";
        System.out.println(Operator.update(sql, new String[] {id}) > 0 ? "删除成功" : "删除失败");
    }

    public static void update(Scanner scan , User user){
        if(user.getIdAdministrator() == 0) return ;
        System.out.print("需要更新的宠物的ID为：");
        String id = scan.nextLine();
        System.out.print("新的宠物的名字为：");
        String name = scan.nextLine();
        System.out.print("新的宠物的品种为：");
        String type = scan.nextLine();
        System.out.print("新的宠物的年龄为：");
        Integer age = scan.nextInt();
        System.out.println("新的宠物的性别为：");
        String gender = scan.nextLine();
        System.out.println("新的宠物的价格为：");
        Integer price = scan.nextInt();

        String sql = "update pet set name = ? , type = ? , age = ? , gender = ? , price = ? where id = ?";
        
        System.out.println(
            Operator.update(sql, new Object[] {name , type , age , gender , price , id}) > 0 ?
            "更新成功" : "更新失败"
        );
    }
}
