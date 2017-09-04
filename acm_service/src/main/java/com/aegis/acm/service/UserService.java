package com.aegis.acm.service;

import com.aegis.acm.domain.User;

public interface UserService {
    User findByUsernameAndPassword(String username, String password);
}
