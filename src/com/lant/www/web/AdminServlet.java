package com.lant.www.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

//处理和我们的管理员相关的内容
@WebServlet("/admin.lt")
public class AdminServlet extends BaseServlet{

    public String login(HttpServletRequest request){
        return "z#login.jsp";
    }

    public String regist(HttpServletRequest request){
        return "r#regist.jsp";
    }

    public String addAdmin(HttpServletRequest request){
        return "addadmin";
    }
}
