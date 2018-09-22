package com.java.crm.dao;

import com.java.crm.domain.BaseDict;

import java.util.List;

public interface BaseDictDao {
    List<BaseDict> findByTypeCode(String dictTypeCode);
}
