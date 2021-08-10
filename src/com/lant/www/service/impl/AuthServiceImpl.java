package com.lant.www.service.impl;

import com.lant.www.dao.AuthDao;
import com.lant.www.dao.impl.AuthDaoImpl;
import com.lant.www.info.AuthInfo;
import com.lant.www.service.AuthService;

import java.util.List;

public class AuthServiceImpl implements AuthService {

    AuthDao authDao = new AuthDaoImpl();

    @Override
    public List<AuthInfo> queryAuths() {
        return authDao.queryAuths();
    }
}
