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

    @GetParam("/addAdmin.do")
    public String jumpAdd(HttpServletRequest req){
        return "/WEB-INF/adminAdd.jsp";
    }

    @GetParam(value = "/addAd.do")
    public String adminAdd(HttpServletRequest req){
        String username = req.getParameter("username");
        String phone = req.getParameter("phone");
        String age = req.getParameter("age");
        String pass = req.getParameter("pass");
        String repass = req.getParameter("repass");

        //1.1 非空校验
        if(StringUtil.isNull(username)){
            req.setAttribute("errorMsg","用户名为空");
            return "WEB-INF/adminAdd.jsp";
        }

        if(StringUtil.isNull(phone)){
            req.setAttribute("errorMsg","手机为空");
            return "WEB-INF/adminAdd.jsp";
        }

        if(StringUtil.isNull(pass) || StringUtil.isNull(repass)){
            req.setAttribute("errorMsg","密码为空");
            return "WEB-INF/adminAdd.jsp";
        }

        if(!pass.equals(repass)){
            req.setAttribute("errorMsg","两次密码不一致");
            return "WEB-INF/adminAdd.jsp";
        }

        //密码加密
        String md5Pass = StringUtil.md5Str(pass);

        AdminInfo adminInfo = new AdminInfo(username,md5Pass,phone,Integer.valueOf(age));

        boolean b = adminService.insertAdmin(adminInfo);

        if (b){
            return "index.jsp";
        }else {
            return "WEB-INF/adminAdd.jsp";
        }
    }
}
