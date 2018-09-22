package com.java.crm.service.impl;

import com.java.crm.dao.CustomerDao;
import com.java.crm.domain.Customer;
import com.java.crm.domain.PageBean;
import com.java.crm.service.CustomerService;
import org.hibernate.criterion.DetachedCriteria;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CustomerServiceImpl implements CustomerService {
    //注入客户的Dao
    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    //业务层保存客户的方法
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    //业务层分页查询客户的方法
    public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        PageBean<Customer> pageBean = new PageBean<Customer>();
        //封装当前页
        pageBean.setCurrPage(currPage);
        //封装每页显示的记录数
        pageBean.setPageSize(pageSize);
        //封装总记录数
        //调用Dao
        Integer totalCount = customerDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        //封装总页数
        Double tc = totalCount.doubleValue();
        Double num = Math.ceil(tc/pageSize);
        pageBean.setTotalPage(num.intValue());
        //封装每页显示数据的集合
        Integer begin = (currPage - 1)*pageSize;
        List<Customer> list = customerDao.findByPage(detachedCriteria,begin,pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    //Service根据ID查询客户的方法
    public Customer findById(long custId) {
        return customerDao.findById(custId);
    }

    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }
}
