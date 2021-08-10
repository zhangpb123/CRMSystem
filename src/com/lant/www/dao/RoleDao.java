package com.lant.www.dao;

import com.lant.www.info.RoleInfo;

import java.util.List;

public interface RoleDao {
    /**
     * 查找所有的Role
     * @return
     */
    List<RoleInfo> queryAllRole();

    boolean bindRoles(int id, int role);

    List<RoleInfo> queryRoleNameByAdminId(int id);
}
