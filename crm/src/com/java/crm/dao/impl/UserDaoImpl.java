package com.java.crm.dao.impl;

import com.java.crm.dao.UserDao;
import com.java.crm.domain.User;

import java.util.List;

/**
 * 用户管理的Dao的实现类
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    //Dao根据用户名和密码进行查询的方法
    public User login(User user) {
        //noinspection JpaQlInspection
        String hql = "from User where userCode = ?0 and userPassword = ?1";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql, user.getUserCode(),user.getUserPassword());

        //判断一下：
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
