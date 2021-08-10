package com.lant.www.web;

import com.lant.www.anno.GetParam;
import com.lant.www.service.RoleService;
import com.lant.www.service.impl.RoleServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class RoleWeb {

    RoleService roleService = new RoleServiceImpl();

    @GetParam("/roleList.do")
    public String roleJump(){
        //查询所有的角色
        return "roleList.jsp";
    }

    @GetParam(value = "/addRole.do",type = "ajax")
    public String addRole(HttpServletRequest request){

        //添加角色
        //1.角色名称
        String roleName = request.getParameter("roleName");

        // 添加
        int roleid = roleService.addRole(roleName);

        //所有权限
        String ids = request.getParameter("ids");

        //保存权限和角色的绑定
        roleService.addRoleAuth(roleid,ids);

        return "<script>windialog.cancel();</scrpit>";
    }
}
