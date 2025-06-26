package com.shannong.util;

import java.sql.*;

public class DBUtil {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/shixun";
        String user = "root";
        String password = "123456";
        Class.forName(driver);
        Connection conn = null;
        conn=DriverManager.getConnection(url, user, password);
        if(conn!=null){
            System.out.println("数据库连接成功");
        }
        return conn;
    }
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {
        if(rs!=null) rs.close();
        if(ps!=null) ps.close();
        if(conn!=null) conn.close();
    }
}
