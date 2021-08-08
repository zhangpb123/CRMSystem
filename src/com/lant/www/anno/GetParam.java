package com.lant.www.anno;

import java.lang.annotation.*;

//加了这个注解,我们对应前端一个访问的请求地址
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GetParam {
    String value();

    //type:代表我们现在这个方法;是怎么样去跳转页面; 默认是转发; redirect 重发; ajax 请求;
    String type() default "";

}
