package com.lant.www.service.impl;

import com.lant.www.dao.RoleDao;
import com.lant.www.dao.impl.RoleDaoImpl;
import com.lant.www.info.RoleInfo;
import com.lant.www.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    RoleDao roleDao = new RoleDaoImpl();

    /**
     * 查找所有的Role
     * @return
     */
    @Override
    public List<RoleInfo> queryAllRole() {
        return roleDao.queryAllRole();
    }

    /**
     * 添加角色并且获取它的id
     * @param roleName
     * @return
     */
    @Override
    public int addRole(String roleName) {

        //1.添加角色
        boolean bl = roleDao.addRole(roleName);

        //2.查询这个角色的id
        int id = roleDao.queryRoleByName(roleName);

        return 0;
    }

    @Override
    public void addRoleAuth(int roleid, String ids) {
        String[] authids = ids.split(",");

        for (String authid:  authids) {
            roleDao.bindAuth(roleid,authid);
        }
    }
}
