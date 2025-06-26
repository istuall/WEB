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

@WebServlet(name = "editInfoServlet", value = "/editInfo")
public class EditInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> result = new HashMap<>();
        try {
            String username = request.getParameter("username");
            String nickname = request.getParameter("nickname");
            String sex = request.getParameter("sex");
            String age = request.getParameter("age");
            String address = request.getParameter("address");

            UserDao userDao = new UserDao();
            UserDto userDto = userDao.selectUserByUsername(username);

            if (userDto != null) {
                userDto.setNickname(nickname);
                userDto.setSex(sex);
                userDto.setAge(Integer.valueOf(age));
                userDto.setAddress(address);
                userDao.updateUser(userDto);
                result.put("msg", "修改信息成功");
            } else {
                result.put("msg", "用户不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "修改信息失败");
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(result));
    }
}
