package com.shannong.service;

import com.shannong.dao.UserDao;
import com.shannong.dto.UserDto;
import com.shannong.util.MD5Encoder;
import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    //UserDao
    private UserDao userDao = new UserDao();

    //登录
    public UserDto login(String username,String password) throws SQLException {
        //首先根据用户名查询用户
        UserDto userDto = userDao.selectUserByUsername(username);

        //如果用户名可以查询到用户并且页面输入的密码和数据库保存的密码一致
        //将数据库里加密的密码和在页面上获取到的用户密码加密后进行比较
        if(userDto!=null&&userDto.getPassword().equals(MD5Encoder.encode(password))){
            return userDto;
        }else{
            throw new RuntimeException("用户名密码不正确");
        }
    }

    //注册
    public void register(UserDto userDto) throws Exception {

        //校验用户名是否被注册
        UserDto result = userDao.selectUserByUsername(userDto.getUsername());

        if(result==null){
            //执行注册逻辑,对用户密码进行加密
            userDto.setPassword(MD5Encoder.encode(userDto.getPassword()));
            userDao.insertUser(userDto);
        }else{
            throw new RuntimeException("用户名已被注册");
        }
    }

    //查询用户信息
    public List<UserDto> selectUserList
    (String username) throws SQLException {

        return userDao.selectUserList(username);

    }
}
