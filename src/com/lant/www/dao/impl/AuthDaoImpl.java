package com.lant.www.dao.impl;

import com.lant.www.dao.AuthDao;
import com.lant.www.info.AuthInfo;
import com.lant.www.util.DBUtil;

import java.util.List;

public class AuthDaoImpl implements AuthDao {

    /**
     * 查询全部权限
     * @return
     */
    @Override
    public List<AuthInfo> queryAuths() {
        String sql = "select aid,aname,apath,adesc,pid,atype from auth";
        return DBUtil.executeDQL(sql,AuthInfo.class);
    }
}
