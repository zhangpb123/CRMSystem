package com.lant.www.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //前端获取方法名
        String methodName = req.getParameter("method");

        if (methodName == null || "".equals(methodName) ){
            resp.sendRedirect("index.jsp");
            return;
        }

        //获取当前类的类
        Class<? extends BaseServlet> cls = this.getClass();

        Method method = null;

        try {
            //通过字节码对象获取了我们的方法
            method = cls.getMethod(methodName,HttpServletRequest.class );
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (method != null){
            // 执行方法
            try {
                //得到方法的返回值
                Object obj = method.invoke(this, req);

                //代表方法没有返回值; 不符合我们自己定义的规则
                if (obj==null || "".equals(obj)){
                    resp.sendRedirect("index.jsp");
                    return;
                }

                //自己定义规则;自己去解析
                //r# 我需要重定向
                //z# 转发
                //没有r#z# 代表当前是需要进行ajax处理
                String result = (String) obj;
                String[] split = result.split("#");

                //判断分割出来的长度是否大于等于2
                if (split.length >= 2){
                    //判断是哪个方式
                    if (split[0].equals("r")){
                        resp.sendRedirect(split[1]);
                    }else if (split[0].equals("z")){
                        req.getRequestDispatcher(split[1]).forward(req,resp);
                    }else{
                        //如果不是重定向,也不是转发;不行;出现运行时异常提醒你
                        throw new RuntimeException("你装的字符写错了");
                    }

                }else {
                    //为ajax方式
                    resp.getWriter().print(result);
                }

                //执行完了目标方法;
                //根据方法的返回值去做页面的跳转;
                //1 重定向
                //2 转发
                //3 ajax请求 ; 不需要处理页面跳转  json信息
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else{
            //将请求转发到我们的首页; 没有告诉我们的servlet你要访问什么内容
            resp.sendRedirect("index.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
