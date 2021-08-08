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

    /**
     * 插入用户信息
     * @param adminInfo
     * @return
     */
    public boolean insertAdmin(AdminInfo adminInfo) {
        String sql = "INSERT INTO admin(acount,pass,phone,age) VALUES(?,?,?,?)";

        //很多同学在这个地方,字段的顺序会弄错;或者说多一个少一个;导致问题
        return DBUtil.executeDML(sql,adminInfo.getAcount(),adminInfo.getPass(),adminInfo.getPhone(),adminInfo.getAge());
    }
}
