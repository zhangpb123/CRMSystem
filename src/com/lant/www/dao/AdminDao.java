package com.lant.www.dao;

import com.lant.www.info.AdminInfo;

public interface AdminDao {

    /**
     * 查询单个用户-根据acount查询
     */
    public AdminInfo queryAdmin(AdminInfo adminInfo);
}
