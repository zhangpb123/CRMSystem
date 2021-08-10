package com.lant.www.service.impl;

import com.lant.www.dao.AdminDao;
import com.lant.www.dao.impl.AdminDaoImpl;
import com.lant.www.info.AdminInfo;
import com.lant.www.info.PageInfo;
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

    @Override
    public boolean delAdminByIds(String[] idArray) {
        return adminDao.delAdminByIds(idArray);
    }

    @Override
    public List<AdminInfo> queryAdmins(AdminInfo adminInfo) {

        //获得总记录数
        int rowCount = adminDao.queryAdminCountWithOther(adminInfo);

        //如果是0需要做处理
        if(rowCount != 0){
            adminInfo.getPg().setRowCount(rowCount);
        }

        //admin的页码对象
        PageInfo pg = adminInfo.getPg();
        //获得当前页码
        int currentPage = pg.getCurrentPage();
        //获得页码容量
        int pageSize = pg.getPageSize();

        //求得开始页码
        int startRow = (currentPage - 1) * pageSize;
        //求得结束页码
        int pageCount = rowCount / pageSize;
        if (rowCount % pageSize != 0){
            pageCount++;
        }

        pg.setPageCount(pageCount);
        pg.setStartRow(startRow);


        List<AdminInfo> adminInfos = adminDao.queryAdmins(adminInfo);

        return adminInfos;
    }


}
