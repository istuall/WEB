package com.shannong.servlet;

import com.alibaba.fastjson2.JSON;
import com.shannong.dao.UserDao;
import com.shannong.dto.UserDto;
import com.shannong.util.MD5Encoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "changePasswordServlet", value = "/changePassword")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> result = new HashMap<>();
        try {
            String username = request.getParameter("username");
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");

            UserDao userDao = new UserDao();
            UserDto userDto = userDao.selectUserByUsername(username);

            if (userDto != null && userDto.getPassword().equals(MD5Encoder.encode(oldPassword))) {
                userDto.setPassword(MD5Encoder.encode(newPassword));
                userDao.updateUser(userDto);
                result.put("msg", "修改密码成功");
            } else {
                result.put("msg", "旧密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "修改密码失败");
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(result));
    }
}
