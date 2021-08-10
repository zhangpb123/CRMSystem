package com.lant.www.web;

import com.google.gson.Gson;
import com.lant.www.anno.GetParam;
import com.lant.www.info.AuthInfo;
import com.lant.www.service.AuthService;
import com.lant.www.service.impl.AuthServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AuthWeb {
    AuthService authService = new AuthServiceImpl();

    @GetParam(value = "/queryAuths.do",type = "ajax")
    public String queryAuths(HttpServletRequest request){
        //查询所有的权限信息
        List<AuthInfo> list = authService.queryAuths();

        //直接返回list的JSON；并不是使用我们的页面跳转
        //我们需要将list转成JSON
        Gson gson = new Gson();
        String s = gson.toJson(list);

        return s;
    }
}
