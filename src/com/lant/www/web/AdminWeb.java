package com.lant.www.web;

import com.lant.www.anno.GetParam;
import com.lant.www.info.AdminInfo;
import com.lant.www.service.AdminService;
import com.lant.www.service.impl.AdminServiceImpl;
import com.lant.www.system.SystemCode;
import com.lant.www.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @GetParam(value = "/addAd.do",type = "redirect")
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

    @GetParam("/adminlist.do")
    public String jumpAdminList(HttpServletRequest req){
        //查询所有的用户
        List<AdminInfo> adminInfos = adminService.queryAllAdmin();
        req.getSession().setAttribute("adminInfos",adminInfos);

        return "adminList.jsp";
    }

    @GetParam("/editJump.do")
    public String jumpAdminEdit(HttpServletRequest request){
        //获取前端传来的id
        String id = request.getParameter("id");

        //从后端利用id查询信息
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setId(Integer.valueOf(id));

        List<AdminInfo> adminInfos = adminService.queryAdminListByInfo(adminInfo);

        if (adminInfos != null && adminInfos.size()>0){
            request.setAttribute("adminInfo",adminInfos.get(0));
            return "editAdmin.jsp";
        }else{
            return "error.jsp";
        }
    }

    @GetParam(value = "/editAd.do",type = "ajax")
    public String editAdmin(HttpServletRequest req){

        String phone = req.getParameter("phone");
        String age = req.getParameter("age");
        String pass = req.getParameter("pass");
        String repass = req.getParameter("repass");

        //1.1 非空校验
        if(StringUtil.isNull(phone)){
            req.setAttribute("errorMsg","手机为空");
            return "editAdmin.jsp";
        }
        if(StringUtil.isNull(age)){
            req.setAttribute("errorMsg","年龄为空");
            return "editAdmin.jsp";
        }
        if(StringUtil.isNull(pass) || StringUtil.isNull(repass)){
            req.setAttribute("errorMsg","密码为空");
            return "editAdmin.jsp";
        }

        if(!pass.equals(repass)){
            req.setAttribute("errorMsg","两次密码不一致");
            return "editAdmin.jsp";
        }

        //密码加密
        String md5Pass = StringUtil.md5Str(pass);
        AdminInfo adminInfo = new AdminInfo(md5Pass,phone,Integer.valueOf(age));

        //获取前台传来的id
        String id = req.getParameter("id");
        adminInfo.setId(Integer.valueOf(id));

        //去编辑用户信息
        boolean b = adminService.updateAdmin(adminInfo);
        return "0000";

    }

    @GetParam(value = "/deleteAdmin.do" , type = "ajax")
    public String deleteAdmin(HttpServletRequest request){
        //获取前端传来的id
        String id = request.getParameter("id");

        boolean b = adminService.deleteAdmin(id);

        if (b){
            return "1111";
        }

        return "0000";
    }

    @GetParam(value = "/delAdminByIds.do",type = "ajax")
    public String delAdminByIds(HttpServletRequest request){
        //获取前端传来的ids
        String ids = request.getParameter("ids");
        String[] idArray = ids.split(",");

        boolean b = adminService.delAdminByIds(idArray);
        if (b){
            return "1111";
        }else {
            return "0000";
        }
    }

    @GetParam(value = "/queryAdminByWhere.do")
    public String queryAdminByWhere(HttpServletRequest request){

        //从前端获取开始和结束时间和用户名
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String username = request.getParameter("username");

        //创建查询对象
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setStart(start);
        adminInfo.setEnd(end);
        adminInfo.setAcount(username);

        //设置session返回前端
        request.getSession().setAttribute("username",username);
        request.getSession().setAttribute("start",start);
        request.getSession().setAttribute("end",end);

        List<AdminInfo> list = adminService.queryAdminListByInfo(adminInfo);

        request.getSession().setAttribute("adminInfos",list);

        return "adminList.jsp";
    }
}
