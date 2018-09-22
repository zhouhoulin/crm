package com.java.crm.service.impl;

import com.java.crm.dao.SaleVisitDao;
import com.java.crm.domain.PageBean;
import com.java.crm.domain.SaleVisit;
import com.java.crm.service.SaleVisitService;
import org.hibernate.criterion.DetachedCriteria;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * 客户拜访记录的业务层的实现类
 */
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {

    //注入客户拜访记录的Dao
    @Resource(name = "saleVisitDao")
    private SaleVisitDao saleVisitDao;

    @Override
    //业务层分页显示拜访记录的方法
    public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        PageBean<SaleVisit> pageBean = new PageBean<>();
        //设置当前页数
        pageBean.setCurrPage(currPage);
        //设置每页显示记录数
        pageBean.setPageSize(pageSize);
        //设置总记录数
        Integer totalCount = saleVisitDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        //设置总页数
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        //设置每页显示的数据的集合
        Integer begin = (currPage - 1) * pageSize;
        List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria,begin,pageSize);
        pageBean.setList(list);
        return pageBean;

    }

    @Override
    public void save(SaleVisit saleVisit) {
        saleVisitDao.save(saleVisit);
    }
}
