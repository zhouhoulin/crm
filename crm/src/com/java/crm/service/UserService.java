package com.java.crm.service;

import com.java.crm.domain.User;

import java.util.List;

/**
 * 用户管理的service
 */
public interface UserService {
    void regist(User user);

    User login(User user);

    List<User> findAll();
}
