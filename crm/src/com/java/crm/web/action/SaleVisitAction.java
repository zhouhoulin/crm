package com.java.crm.web.action;

import com.java.crm.domain.PageBean;
import com.java.crm.domain.SaleVisit;
import com.java.crm.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 客户拜访记录的Action类
 */
public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

    //模型驱动使用的对象
    private SaleVisit saleVisit = new SaleVisit();

    @Override
    public SaleVisit getModel() {
        return saleVisit;
    }

    /**
     * 在Aciton中注入Service
     */
    @Resource(name="saleVisitService")
    private SaleVisitService saleVisitService;

    //注入分页数据
    private Integer currPage = 1;
    private Integer pageSize = 3;

    public void setCurrPage(Integer currPage) {
        if(currPage == null){
            currPage = 1;
        }
        this.currPage = currPage;
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize == null){
            pageSize = 3;
        }
        this.pageSize = pageSize;
    }

    //接收数据
    private Date visitEndtime;

    public Date getVisitEndtime() {
        return visitEndtime;
    }

    public void setVisitEndtime(Date visitEndtime) {
        this.visitEndtime = visitEndtime;
    }

    /**
     * 查询拜访记录列表的方法：findAll
     * @return
     */
    public String findAll(){
        //创建离线条件查询列表
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
        //设置条件
        if(saleVisit.getVisitTime() != null){
            detachedCriteria.add(Restrictions.ge("visitTime",saleVisit.getVisitTime()));
        }
        if(visitEndtime != null){
            detachedCriteria.add(Restrictions.le("visitTime",visitEndtime));
        }
        //调用业务层
        PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria,currPage,pageSize);
        //存入到值栈
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }

    /**
     * 拜访记录跳转到添加页面的方法：saveUI
     * @return
     */
    public String saveUI(){
        return "saveUI";
    }
    /**
     * 保存客户拜访记录的方法：save
     * @return
     */
    public String save(){
        //调用业务层
        saleVisitService.save(saleVisit);
        return "saveSuccess";
    }
}
