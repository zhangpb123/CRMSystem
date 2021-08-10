package com.lant.www.dao;

import com.lant.www.info.AuthInfo;

import java.util.List;

public interface AuthDao {
    /**
     * 查询全部权限
     * @return
     */
    List<AuthInfo> queryAuths();
}
