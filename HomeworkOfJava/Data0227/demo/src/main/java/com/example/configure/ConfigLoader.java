package com.example.configure;


import com.alibaba.druid.pool.DruidDataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class ConfigLoader {
    public static DruidDataSource loadFromProperties() throws Exception {
        try (InputStream input = ConfigLoader.class.getResourceAsStream("/druid.properties")) {
            Properties props = new Properties();
            props.load(input);
            
            DruidDataSource ds = new DruidDataSource();
            ds.configFromPropeties(props);;
            ds.init();                  
            return ds;
        }
    }

    public static Connection getConnection() throws Exception{
        try(DruidDataSource ds = loadFromProperties()){
            return ds.getConnection();
        }
    }
}
