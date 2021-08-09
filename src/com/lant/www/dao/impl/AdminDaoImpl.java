package com.lant.www.dao.impl;

import com.lant.www.dao.AdminDao;
import com.lant.www.info.AdminInfo;
import com.lant.www.util.DBUtil;
import com.lant.www.util.StringUtil;

import java.util.List;

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

    /**
     * 查询全部用户
     */
    public List<AdminInfo> queryAllAdmin(){
        String sql="SELECT id,acount,pass,phone,age,createtime,updatetime,astatus,adesc FROM admin";
        return DBUtil.executeDQL(sql,AdminInfo.class);
    }

    /**
     * 根据不同的条件去查询不同的信息
     * @param adminInfo
     * @return
     */
    @Override
    public List<AdminInfo> queryAdminListByInfo(AdminInfo adminInfo) {

        String sql = "SELECT id,acount,pass,phone,age,createtime,updatetime,astatus,adesc FROM admin where 1=1";

        //拓展: 我们如何才能够动态的把参数赋值给我们的通用方法;
        //提示: 使用数组的方式

        if(adminInfo.getId() != 0){ //说明我们这个id是有值;就代表我需要根据id去搜索
            sql += " and id = " + adminInfo.getId();
        }
        if(!StringUtil.isNull(adminInfo.getPhone())){
            sql += " and phone = " + adminInfo.getPhone();
        }
        if(adminInfo.getAge() != 0){
            sql += " and age = " + adminInfo.getAge();
        }


        return DBUtil.executeDQL(sql,AdminInfo.class);
    }

    /**
     * 保存修改的信息
     * @param adminInfo
     * @return
     */
    @Override
    public boolean updateAdmin(AdminInfo adminInfo) {
        String sql = "UPDATE admin SET phone=?,age=?,pass=? WHERE id=?";
        return DBUtil.executeDML(sql,adminInfo.getPhone(),adminInfo.getAge(),adminInfo.getPass(),adminInfo.getId()); //给参数的时候,顺序不要给错了
    }

    /**
     * 根据ID删除用户
     * @param id
     * @return
     */
    @Override
    public boolean deleteAdminById(String id) {
        String sql = "DELETE FROM admin WHERE id = ?";
        return DBUtil.executeDML(sql, id);
    }
}
