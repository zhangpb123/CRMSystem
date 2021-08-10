package com.lant.www.service;


import com.lant.www.info.RoleInfo;
import java.util.List;

public interface RoleService {
    List<RoleInfo> queryAllRole();

    int addRole(String roleName);

    void addRoleAuth(int roleid, String ids);
}
