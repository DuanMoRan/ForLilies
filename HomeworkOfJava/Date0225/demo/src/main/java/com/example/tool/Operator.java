package com.example.tool;

import com.example.configure.ConfigLoader;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Operator {
    public static int update(String sql , Object[] objects){
        try(Connection conn = ConfigLoader.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sql);
            for(int i = 0 ; i < objects.length ; i ++){
                statement.setObject(i, objects[i]);
            }

            int row = statement.executeUpdate();
            statement.close();
            return row;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static <T> List<T> query(Class<T> cla , String sql , Object[] objects){
        try(Connection conn = ConfigLoader.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sql);
            for(int i = 0 ; i < objects.length ; i ++){
                statement.setObject(i, objects[i]);
            }

            List<T> list = new ArrayList<>();

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                T obj = cla.getDeclaredConstructor().newInstance();
                Field[] fields = cla.getDeclaredFields();

                for(Field field : fields){
                    Object value = rs.getObject(field.getName());
                    field.setAccessible(true);
                    field.set(obj, value);
                }

                list.add(obj);
            }

            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
}
