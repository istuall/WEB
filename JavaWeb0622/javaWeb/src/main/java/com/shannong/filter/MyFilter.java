package com.shannong.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "myFilter",value = {"/*"})
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        System.out.println(request.getServletPath());
        String path = request.getServletPath();
        if(path.equals("/html/login.html")||path.equals("/login")
                ||path.equals("/html/register.html")||path.equals("/register")
        ||path.endsWith(".css")||path.endsWith(".js")){
            //直接放行让请求访问目标地址
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            //访问需要登录状态的目标，需要校验登录状态
            String username = (String)request.getSession().getAttribute("username");
            if(username!=null&&!"".equals(username)){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                response.sendRedirect("/html/login.html");
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁方法执行");

    }
}
