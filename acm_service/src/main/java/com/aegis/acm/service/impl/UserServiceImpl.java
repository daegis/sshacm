package com.aegis.acm.service.impl;

import com.aegis.acm.commons.SystemLog;
import com.aegis.acm.dao.UserDao;
import com.aegis.acm.domain.User;
import com.aegis.acm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @SystemLog(description = "执行登录")
    public User findByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    @SystemLog(description = "修改密码")
    @Override
    public void save(User dbuser) {
        userDao.save(dbuser);
    }
}
