package com.java.crm.service;

import com.java.crm.domain.Customer;
import com.java.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerService {

    void save(Customer customer);

    PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

    Customer findById(long custId);

    void delete(Customer customer);

    void update(Customer customer);

    List<Customer> findAll();
}
