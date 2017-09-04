package com.aegis.acm.dao;

import com.aegis.acm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String password);
}
