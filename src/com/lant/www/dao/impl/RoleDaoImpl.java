package com.lant.www.dao.impl;

import com.lant.www.dao.RoleDao;
import com.lant.www.info.RoleInfo;
import com.lant.www.util.DBUtil;

import java.util.List;

public class RoleDaoImpl implements RoleDao {

    /**
     * 查找所有的Role
     * @return
     */
    @Override
    public List<RoleInfo> queryAllRole() {
        String sql = "SELECT rid,rname,rstatus,rdesc,rtime FROM role";

        return DBUtil.executeDQL(sql,RoleInfo.class);
    }

    @Override
    public boolean bindRoles(int id, int role) {
        String sql = "INSERT INTO admin_role(adminid,roleid) VALUES (?,?)";
        return DBUtil.executeDML(sql,id,role);
    }

    @Override
    public List<RoleInfo> queryRoleNameByAdminId(int id) {
        String sql = "SELECT rid,rname,rstatus,rdesc,rtime FROM admin_role ar LEFT JOIN ROLE r ON ar.roleid = r.rid WHERE ar.adminid = ?";
        return DBUtil.executeDQL(sql,RoleInfo.class,id);
    }

    //添加角色
    @Override
    public boolean addRole(String roleName) {
        String sql = "insert into role(rname) values(?)";
        return DBUtil.executeDML(sql,roleName);
    }

    //根据角色名称查询角色id
    @Override
    public int queryRoleByName(String roleName) {
        String sql = "select rid from role where rname = '" + roleName +"'";
        return DBUtil.executeDQLGetCount(sql);//并不是查询角色总数，而是查询角色的id
    }

    @Override
    public void bindAuth(int roleid, String authid) {
        String sql = "insert into role_auth(roleid,authid) values(?,?)";
        DBUtil.executeDML(sql,roleid,authid);
    }
}
