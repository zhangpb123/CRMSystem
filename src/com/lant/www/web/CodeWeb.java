package com.lant.www.web;

import cn.dsna.util.images.ValidateCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/code")
public class CodeWeb extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //创建一个验证码；验证码的宽高；数字的个数；干扰线
        ValidateCode vc = new ValidateCode(230,40,6,55);

        //禁止图像缓存
        resp.setHeader("Pragma","no-cache");
        resp.setHeader("Cache-Control","no-cache");
        resp.setDateHeader("Expires",0);

        //获取验证码的值
        String code = vc.getCode();
        req.getSession().setAttribute("code",code);

        System.out.println(code);

        //将图片验证码输出到前台页面
        vc.write(resp.getOutputStream());
    }
}

