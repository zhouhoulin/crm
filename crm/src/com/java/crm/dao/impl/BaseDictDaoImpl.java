package com.java.crm.dao.impl;

import com.java.crm.dao.BaseDictDao;
import com.java.crm.domain.BaseDict;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * 字典Dao的实现类
 */
public class BaseDictDaoImpl extends HibernateDaoSupport implements BaseDictDao {

    @Override
    //根据类型编码查询字典数据
    public List<BaseDict> findByTypeCode(String dictTypeCode) {
        return (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dictTypeCode = ?0",
                dictTypeCode);
    }
}
