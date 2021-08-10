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
}
