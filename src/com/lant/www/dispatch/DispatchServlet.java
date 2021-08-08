package com.lant.www.dispatch;

import com.lant.www.anno.GetParam;
import com.lant.www.bean.GetParamBean;
import com.lant.www.util.ClassUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispatchServlet implements Servlet {

    //专门用来存放我们请求对应需要哪个实体类处理
    private static Map<String,GetParamBean> config = new HashMap<String,GetParamBean>();


    //当我们servlet被创建的时候;代表会调用这个方法
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //获取我们在web.xml里面配置的init-param参数
        //包名
        String packPath = servletConfig.getInitParameter("packPath");

        /**
         * 1: 通过包名获取包下面所有的类;
         * 2: 找到这个类里面所有的方法
         * 3: 找到方法上面所有的GetParam注解的内容
         */

        //通过工具类的方法;获取指定包下面所有的字节码对象
        List<Class> cls = ClassUtil.getClasssFromPackage(packPath);

        //遍历我们的字节码集合对象 ; 获取这个里面的每一个有GetParam注解的方法;
        for (Class c : cls) {
            //得到类自己的所有方法
            Method[] methods = c.getDeclaredMethods();

            //判断这个方法是否有我们指定的注解
            for (Method m : methods) {
                if (m.isAnnotationPresent(GetParam.class)){
                    //得到注解
                    GetParam annotation = m.getAnnotation(GetParam.class);
                    //就是对应我们请求的Path
                    String requestPath = annotation.value();

                    //存储当前的类和方法
                    GetParamBean bean = new GetParamBean();

                    bean.setCls(c);
                    bean.setMethod(m);

                    //保存对应关系
                    config.put(requestPath,bean);
                }
            }
        }
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        //需要根据请求，去执行对应的方法
        //1.获取请求的名称
        String requestURI = request.getRequestURI();
        String path = requestURI.substring(requestURI.lastIndexOf("/"));

        //2.去config中查找
        GetParamBean bean = config.get(path);
        if (bean == null){
            response.sendRedirect("error.jsp");
            return;
        }

        //3.执行
        Method method = bean.getMethod();//需要执行的目标方法
        Class cls = bean.getCls();//获取字节码对象
        try {
            Object o = cls.newInstance();//通过字节码对象得到一个实体类的对象

            //判断当前这个方法时候需要我们的HttpServletRequest参数; 如果这个方法不需要,执行的时候,就不用传递参数
            Class<?>[] parameterTypes = method.getParameterTypes();//获取我们方法的参数

            //判断执行方法是否需要request参数;
            Object invoke = null;
            if(null != parameterTypes && parameterTypes.length == 1 && parameterTypes[0] == HttpServletRequest.class){
                invoke = method.invoke(o, request);//执行目标方法,需要传递参数
            }else{
                invoke = method.invoke(o);
            }

            String result = (String)invoke;//返回的结果

            //4.返回结果
            GetParam annotation = method.getAnnotation(GetParam.class);
            String type = annotation.type();

            if ("redirect".equalsIgnoreCase(type)){
                //重定向
                response.sendRedirect(result);
            }else if ("ajax".equalsIgnoreCase(type)){
                response.getWriter().print(result);
            }else{
                request.getRequestDispatcher(result).forward(request,response);
            }


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
