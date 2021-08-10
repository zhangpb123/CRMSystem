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
}
