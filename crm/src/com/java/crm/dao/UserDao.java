package com.java.crm.dao;

import com.java.crm.domain.User;

/**
 * 用户管理的Dao接口
 */
public interface UserDao extends BaseDao<User>{

    User login(User user);
}
