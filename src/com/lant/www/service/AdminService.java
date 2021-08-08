package com.lant.www.service;

import com.lant.www.info.AdminInfo;

public interface AdminService {

    /**
     * login查询单个用户-根据acount查询
     */
    public AdminInfo login(String acount);


}
