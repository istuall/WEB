package com.shannong.servlet;

import com.alibaba.fastjson2.JSON;
import com.shannong.dto.UserDto;
import com.shannong.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "loginServlet", value = "/login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> result = new HashMap<>();
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            UserService userService = new UserService();
            UserDto userDto = userService.login(username, password);
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            result.put("nickname", userDto.getNickname());
            result.put("msg", "请求成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", e.getMessage());
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(result));
    }
}
