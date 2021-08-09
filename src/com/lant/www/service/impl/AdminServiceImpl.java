package com.lant.www.service.impl;

import com.lant.www.dao.AdminDao;
import com.lant.www.dao.impl.AdminDaoImpl;
import com.lant.www.info.AdminInfo;
import com.lant.www.service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    AdminDao adminDao = new AdminDaoImpl();

    /**
     * login查询单个用户-利用根据acount查询
     */
    @Override
    public AdminInfo login(String acount) {
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setAcount(acount);
        return adminDao.queryAdmin(adminInfo);
    }

    @Override
    public boolean insertAdmin(AdminInfo adminInfo) {
        return adminDao.insertAdmin(adminInfo);
    }

    @Override
    public List<AdminInfo> queryAllAdmin() {
        return adminDao.queryAllAdmin();
    }

    @Override
    public List<AdminInfo> queryAdminListByInfo(AdminInfo adminInfo) {
        return adminDao.queryAdminListByInfo(adminInfo);
    }

    @Override
    public boolean updateAdmin(AdminInfo adminInfo) {
        return adminDao.updateAdmin(adminInfo);
    }

    @Override
    public boolean deleteAdmin(String id) {
        return adminDao.deleteAdminById(id);
    }


}
