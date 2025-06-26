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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name="userListServlet",value = "/userList")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String,Object> result = new HashMap<>();
        String username = req.getParameter("username");
        try{
            UserService userService = new UserService();

            List<UserDto> list = userService.selectUserList(username);
            result.put("data",list);
            result.put("msg","请求成功");
        }catch (Exception e){
            e.printStackTrace();
            result.put("msg",e.getMessage());
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        PrintWriter printWriter = resp.getWriter();
        printWriter.write(JSON.toJSONString(result));

    }
}
