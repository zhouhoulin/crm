package com.java.crm.service;

import com.java.crm.domain.PageBean;
import com.java.crm.domain.SaleVisit;
import org.hibernate.criterion.DetachedCriteria;

/**
 * 客户拜访记录业务层的接口
 */
public interface SaleVisitService {

    PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

    void save(SaleVisit saleVisit);
}
