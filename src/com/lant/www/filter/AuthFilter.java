package com.lant.www.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =  (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();

        //和登录业务相关; 登录的页面, 登录的请求等等
        if (requestURI.contains("login") || requestURI.contains("css") || requestURI.contains(".js") || requestURI.contains(".jpg") ||
        requestURI.contains(".png") || requestURI.contains("code")
        ){
            //不需要登录就可以访问的请求
            filterChain.doFilter(request,response);
        }else{
            //登录成功以后;会将用户信息保存到我们的会话里面
            Object useName = request.getSession().getAttribute("useName");
            if (useName == null){
                response.sendRedirect("login.jsp");
            }else {
                filterChain.doFilter(request,response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
