package com.java.crm.web.action;

import com.java.crm.domain.Customer;
import com.java.crm.domain.LinkMan;
import com.java.crm.domain.PageBean;
import com.java.crm.service.CustomerService;
import com.java.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

    //模型驱动使用的对象
    private LinkMan linkMan = new LinkMan();
    @Override
    public LinkMan getModel() {
        return linkMan;
    }

    //注入service
    private LinkManService linkManService;

    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }


    //注入客户管理的customerService
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

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

    /**
     *
     * @return
     */
    public String findAll(){
        //创建离线条件查询
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
        //设置条件
        if(linkMan.getLkmName() != null){
            //设置按名称查询的条件
            detachedCriteria.add(Restrictions.like("lkmName","%"+linkMan.getLkmName()+"%"));
        }
        //设置性别条件
        if(linkMan.getLkmGender() != null && !"".equals(linkMan.getLkmGender())){
            //设置按性别查询条件
            detachedCriteria.add(Restrictions.eq("lkmGender",linkMan.getLkmGender()));
        }
        //调用业务层
        PageBean<LinkMan> pageBean = linkManService.findAll(detachedCriteria,currPage,pageSize);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";

    }
    /**
     * 跳转添加到页面的方法saveUI
     */
    public String saveUI(){
        //查询所有客户：
        List<Customer> list = customerService.findAll();
        //将list集合保存值栈中
        ActionContext.getContext().getValueStack().set("list",list);
        return "saveUI";
    }

    /**
     * 保存联系人的方法：save
     * @return
     */
    public String save(){
        //调用业务层保存联系人
        linkManService.save(linkMan);
        return "saveSuccess";
    }

    /**
     * 跳转到编辑页面的方法：edit
     * @return
     */
    public String edit(){
        //查询某个联系人,查询所有客户
        //查询所有客户
        List<Customer> list = customerService.findAll();
        //根据ID查询联系人
        linkMan = linkManService.findById(linkMan.getLkmId());
        //将list和linkMan的对象带到页面上
        ActionContext.getContext().getValueStack().set("list",list);
        //将对象的值存到到值栈
        ActionContext.getContext().getValueStack().push(linkMan);
        return "editSuccess";
    }

    /**
     * 修改联系人的方法：update
     * @return
     */
    public String update(){
        //调用业务层
        linkManService.update(linkMan);
        return "updateSuccess";
    }

    public String delete(){
        //调用业务层
        linkMan = linkManService.findById(linkMan.getLkmId());
        //删除联系人
        linkManService.delete(linkMan);
        //页面跳转
        return "deleteSuccess";
    }
}
