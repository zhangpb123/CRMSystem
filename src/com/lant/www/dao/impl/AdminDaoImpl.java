package com.lant.www.dao.impl;

import com.lant.www.dao.AdminDao;
import com.lant.www.info.AdminInfo;
import com.lant.www.util.DBUtil;

public class AdminDaoImpl implements AdminDao {

    /**
     * 查询单个用户-根据acount查询
     */
    public AdminInfo queryAdmin(AdminInfo adminInfo){
        String sql = "SELECT id,acount,pass,phone,age,createtime,updatetime,astatus,adesc FROM admin where acount = ?";
        return DBUtil.executeDQLGetOne(sql,AdminInfo.class,adminInfo.getAcount());
    }


}
