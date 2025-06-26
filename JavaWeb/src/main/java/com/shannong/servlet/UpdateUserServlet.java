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
import java.util.Map;

@WebServlet(name = "updateUserServlet", value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> result = new HashMap<>();
        try {
            UserDto user = new UserDto();
            user.setId(Integer.parseInt(request.getParameter("id")));
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setNickname(request.getParameter("nickname"));
            user.setSex(request.getParameter("sex"));
            user.setAge(Integer.parseInt(request.getParameter("age")));
            user.setAddress(request.getParameter("address"));

            UserDao userDao = new UserDao();
            userDao.updateUser(user);
            result.put("msg", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "修改失败：" + e.getMessage());
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(result));
    }
}
