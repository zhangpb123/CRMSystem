package com.lant.www.system;

/**
 * 系统的一些常量值的定义
 */
public interface SystemCode {

    /*用户登录的区间10000-19999*/
    int USER_LOGIN_SUCCESS = 10000;
    int USER_LOGIN_NULL_USER = 10001;
    int USER_LOGIN_PASS_WRONG= 10002;

    String USER_LOGIN_NULL_USER_STR = "用户名为空";
    String USER_LOGIN_NULL_PASS_STR = "密码为空";
    String USER_LOGIN_NULL_CODE_STR = "验证码为空";
    String USER_LOGIN_ERROR_CODE_STR = "验证码错误";
    String USER_REGIST_SUCCESS = "注册成功";

    String SYSTEM_ERROR = "系统异常";

    String USER_UPDATE_SUCCESS = "修改成功";

    /*用户注册的区间20000-21000*/
    int USER_REGIST_NAME = 20001;//代表用户名已经存在
}
