package com.lant.www.bean;

import java.lang.reflect.Method;

/**
 * 注解实体类
 */
public class GetParamBean {
    private Class cls; //对应的字节码对象
    private Method method; //对应需要执行的方法

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
