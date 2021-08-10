package com.lant.www.service;

import com.lant.www.info.AuthInfo;

import java.util.List;

public interface AuthService {

    /**
     * 查询全部权限
     * @return
     */
    List<AuthInfo> queryAuths();
}
