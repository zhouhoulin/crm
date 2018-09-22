package com.java.crm.service;

import com.java.crm.domain.BaseDict;

import java.util.List;

public interface BaseDictService {
    List<BaseDict> findByTypeCode(String dictTypeCode);
}
