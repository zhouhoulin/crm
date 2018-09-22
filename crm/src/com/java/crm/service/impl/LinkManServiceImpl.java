package com.java.crm.service.impl;

import com.java.crm.dao.LinkManDao;
import com.java.crm.domain.LinkMan;
import com.java.crm.domain.PageBean;
import com.java.crm.service.LinkManService;
import org.hibernate.criterion.DetachedCriteria;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class LinkManServiceImpl implements LinkManService {
    private LinkManDao linkManDao;

    public void setLinkManDao(LinkManDao linkManDao) {
        this.linkManDao = linkManDao;
    }

    @Override
    public PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        PageBean<LinkMan> pageBean = new PageBean<>();
        //设置当前页数
        pageBean.setCurrPage(currPage);
        //设置每页显示记录数
        pageBean.setPageSize(pageSize);
        //设置总记录数
        Integer totalCount = linkManDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        //设置总页数
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        //每页显示数据的集合
        Integer begin = (currPage - 1) * pageSize;
        List<LinkMan> list = linkManDao.findByPage(detachedCriteria,begin,pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public void save(LinkMan linkMan) {
        linkManDao.save(linkMan);
    }

    @Override
    public LinkMan findById(long lkmId) {
        return linkManDao.findById(lkmId);
    }

    @Override
    public void update(LinkMan linkMan) {
        linkManDao.update(linkMan);
    }

    @Override
    public void delete(LinkMan linkMan) {
        linkManDao.delete(linkMan);
    }
}
