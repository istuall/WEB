package com.shannong.servlet;

import com.alibaba.fastjson2.JSON;
import com.shannong.dao.UserDao;
import com.shannong.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "userListServlet", value = "/userList")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> result = new HashMap<>();
        try {
            String username = request.getParameter("username");
            UserDao userDao = new UserDao();
            List<UserDto> userList = userDao.selectUserList(username);
            result.put("data", userList);
            result.put("msg", "请求成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "查询失败：" + e.getMessage());
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(result));
    }
}
