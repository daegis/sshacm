package com.aegis.acm.service.impl;

import com.aegis.acm.dao.UserDao;
import com.aegis.acm.domain.User;
import com.aegis.acm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    @Override
    public void save(User dbuser) {
        userDao.save(dbuser);
    }
}
