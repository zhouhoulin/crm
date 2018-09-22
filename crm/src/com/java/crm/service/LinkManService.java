package com.java.crm.service;

import com.java.crm.domain.LinkMan;
import com.java.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface LinkManService {
    PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

    void save(LinkMan linkMan);

    LinkMan findById(long lkmId);

    void update(LinkMan linkMan);

    void delete(LinkMan linkMan);
}
