package com.lant.www.web;

import com.lant.www.anno.GetParam;

import javax.servlet.http.HttpServletRequest;

public class AdminWeb {

    @GetParam("/login.do")
    public String login(HttpServletRequest request){
        return "login.jsp";
    }

    @GetParam(value = "/regist.do",type = "post")
    public String regist(HttpServletRequest request){
        return "regist.jsp";
    }

    @GetParam(value = "/adminlist.do",type = "ajax")
    public String adminlist(HttpServletRequest request){
        return "adminlist";
    }
}
