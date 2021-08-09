package com.lant.www.dao;

import com.lant.www.info.AdminInfo;

import java.util.List;

public interface AdminDao {

    /**
     * 查询单个用户-根据acount查询
     */
    public AdminInfo queryAdmin(AdminInfo adminInfo);

    /**
     * 插入用户信息
     * @param adminInfo
     * @return
     */
    public boolean insertAdmin(AdminInfo adminInfo) ;

    /**
     * 查询全部用户
     */
    public List<AdminInfo> queryAllAdmin();

    /**
     * 根据不同的条件去查询不同的信息
     * @param adminInfo
     * @return
     */
    public List<AdminInfo> queryAdminListByInfo(AdminInfo adminInfo);

    /**
     * 保存修改的信息
     * @param adminInfo
     * @return
     */
    public boolean updateAdmin(AdminInfo adminInfo);

    boolean deleteAdminById(String id);
}
