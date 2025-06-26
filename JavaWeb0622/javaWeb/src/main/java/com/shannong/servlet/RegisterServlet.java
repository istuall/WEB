package com.shannong.servlet;

import com.alibaba.fastjson2.JSON;
import com.shannong.dto.UserDto;
import com.shannong.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "registerServlet",value = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Map<String,Object> result = new HashMap<>();
        try{
            //获取用户名密码
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String nickname = request.getParameter("nickname");
            String sex = request.getParameter("sex");
            String age = request.getParameter("age");
            String address = request.getParameter("address");
            UserDto userDto = new UserDto();
            userDto.setUsername(username);
            userDto.setPassword(password);
            userDto.setNickname(nickname);
            userDto.setAge(Integer.valueOf(age));
            userDto.setSex(sex);
            userDto.setAddress(address);

            //调用UserService的登录方法
            UserService userService = new UserService();

            //执行注册逻辑
            userService.register(userDto);

            result.put("msg","请求成功");
        }catch (Exception e){
            e.printStackTrace();
            result.put("msg",e.getMessage());
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(result));

    }


}
