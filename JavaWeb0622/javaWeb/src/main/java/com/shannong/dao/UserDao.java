package com.shannong.dao;


import com.mysql.cj.jdbc.Driver;
import com.shannong.dto.UserDto;
import com.shannong.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDao {

    public static void main(String[] args) throws Exception{
        UserDao userDao = new UserDao();
//        userDao.insertUser();
        UserDto userDto = userDao.selectUserByUsername("13350153446");
        System.out.println(userDto);
    }

    public List<UserDto> selectUserList(String username) throws SQLException {
        //获得连接对象
        Connection conn = DBUtil.getConn();
        String selectSql =  "select id,username,password,nickname,sex,age,address,insert_date from user";
        if(username!=null&&!"".equals(username)){
            selectSql+=" where username = ?";
        }
        //sql语句执行平台
        PreparedStatement statement = conn.prepareStatement(selectSql);
        if(username!=null&&!"".equals(username)){
            //形参替换
            statement.setString(1,username);
        }
        //执行语句
        ResultSet resultSet = statement.executeQuery();
        List<UserDto> list = new ArrayList<>();
        while (resultSet.next()){
            UserDto userDto =  new UserDto();
            userDto.setId(resultSet.getInt(1));
            userDto.setUsername(resultSet.getString(2));
            userDto.setPassword(resultSet.getString(3));
            userDto.setNickname(resultSet.getString(4));
            userDto.setSex(resultSet.getString(5));
            userDto.setAge(resultSet.getInt(6));
            userDto.setAddress(resultSet.getString(7));
            userDto.setInsertDate(resultSet.getDate(8));
            //把对象放到集合中
            list.add(userDto);
        }
        //关闭资源
        resultSet.close();
        statement.close();
        conn.close();
        return list;
    }

    public UserDto selectUserByUsername(String username) throws SQLException {
        //获得连接对象
        Connection conn = DBUtil.getConn();

        //查询的sql语句
        String selectSql = "select id,username,password,nickname,sex,age,address,insert_date from user where username = ?";

        //获取Sql语句执行平台
        PreparedStatement statement = conn.prepareStatement(selectSql);
        //把形参替换为实参
        statement.setString(1,username);
        //执行sql语句
        ResultSet resultSet =  statement.executeQuery();

        UserDto userDto = null;

        //通过遍历指向结果的每一行数据
        while (resultSet.next()){
            userDto =  new UserDto();
            userDto.setId(resultSet.getInt(1));
            userDto.setUsername(resultSet.getString(2));
            userDto.setPassword(resultSet.getString(3));
            userDto.setNickname(resultSet.getString(4));
            userDto.setSex(resultSet.getString(5));
            userDto.setAge(resultSet.getInt(6));
            userDto.setAddress(resultSet.getString(7));
            userDto.setInsertDate(resultSet.getDate(8));
        }
        //关闭
        resultSet.close();
        statement.close();
        conn.close();
        return userDto;

    }

    public void insertUser(UserDto userDto) throws Exception{


        //获得连接
        Connection connection = DBUtil.getConn();

        //插入语句
        String insertSql = "insert into user(username,password,nickname,sex,age,address,insert_date) " +
                "values (?,?,?,?,?,?,CURDATE())";

        //获得sql语句预处理平台
        PreparedStatement pds = connection.prepareStatement(insertSql);

        //将形参替换为实际的值
        pds.setString(1,userDto.getUsername());
        pds.setString(2,userDto.getPassword());
        pds.setString(3,userDto.getNickname());
        pds.setString(4,userDto.getSex());
        pds.setInt(5,userDto.getAge());
        pds.setString(6,userDto.getAddress());

        //执行插入语句
        int count =  pds.executeUpdate();

        System.out.println("操作影响的行数"+count);
        //关闭连接
        pds.close();
        connection.close();



    }
}
