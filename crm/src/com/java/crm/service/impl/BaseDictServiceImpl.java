package com.java.crm.service.impl;

import com.java.crm.dao.BaseDictDao;
import com.java.crm.domain.BaseDict;
import com.java.crm.service.BaseDictService;

import java.util.List;

public class BaseDictServiceImpl implements BaseDictService {
    //注入Dao
    private BaseDictDao baseDictDao;

    public void setBaseDictDao(BaseDictDao baseDictDao) {
        this.baseDictDao = baseDictDao;
    }

    @Override
    public List<BaseDict> findByTypeCode(String dictTypeCode) {
        return baseDictDao.findByTypeCode(dictTypeCode);
    }
}
