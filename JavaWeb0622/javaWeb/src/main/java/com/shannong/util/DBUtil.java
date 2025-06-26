package com.shannong.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static String url = "jdbc:mysql://localhost:3306/shixun";
    private static String user = "root";
    private static String password = "123456";

    static {
        try{
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    //返回连接对象
    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
}
