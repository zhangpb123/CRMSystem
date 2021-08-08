package com.lant.www.web;

import com.lant.www.anno.GetParam;
import com.lant.www.info.AdminInfo;
import com.lant.www.service.AdminService;
import com.lant.www.service.impl.AdminServiceImpl;
import com.lant.www.system.SystemCode;
import com.lant.www.util.StringUtil;

import javax.servlet.http.HttpServletRequest;

public class AdminWeb {

    AdminService adminService = new AdminServiceImpl();

    @GetParam(value = "/login.do")
    public String login(HttpServletRequest req){

        //登录业务的处理
        //获取前端传来的用户名和密码
        String userName = req.getParameter("userName");
        String userPass = req.getParameter("userPass");

        req.getSession().setAttribute("name",userName);

        //验证码
        String userCode = req.getParameter("userCode");

        //非空判断
        if (userName == null || "".equals(userName)){
            req.getSession().setAttribute("errorMsg", SystemCode.USER_LOGIN_NULL_USER_STR);
            return "login.jsp";
        }
        if (userPass == null || "".equals(userPass)){
            req.getSession().setAttribute("errorMsg", SystemCode.USER_LOGIN_NULL_PASS_STR);
            return "login.jsp";
        }
        if (userCode == null || "".equals(userCode)){
            req.getSession().setAttribute("errorMsg", SystemCode.USER_LOGIN_NULL_CODE_STR);
            return "login.jsp";
        }

        //验证码是否正确
        String code = String.valueOf(req.getSession().getAttribute("code")) ;
        if (userCode == null || "".equals(userCode)){
            req.getSession().setAttribute("errorMsg", SystemCode.SYSTEM_ERROR);
            return "login.jsp";
        }

        if(!code.equals(userCode)){
            req.getSession().setAttribute("errorMsg", SystemCode.USER_LOGIN_ERROR_CODE_STR);
            return "login.jsp";
        }else{
            String md5Str = StringUtil.md5Str(userPass);
            System.out.println(md5Str);
            AdminInfo adminInfo = adminService.login(userName);

            if (adminInfo != null){
                if (md5Str.equals(adminInfo.getPass())){
                    //设置Session变量存储user
                    req.getSession().setAttribute("useName",adminInfo.getAcount());
                    return "index.jsp";
//                req.getRequestDispatcher("userlist").forward(req,resp);
                }else{
                    req.getSession().setAttribute("errorMsg", SystemCode.USER_LOGIN_ERROR_PASS_STR);
                    return "login.jsp";
                }

            }
        }

        System.out.println("验证完毕");

        return "login.jsp";
    }

    @GetParam(value = "/regist.do")
    public String regist(HttpServletRequest request){
        return "regist.jsp";
    }

    @GetParam(value = "/adminlist.do",type = "ajax")
    public String adminlist(HttpServletRequest request){
        return "adminlist";
    }
}
