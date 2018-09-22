package com.java.crm.web.action;

import com.java.crm.domain.Customer;
import com.java.crm.domain.PageBean;
import com.java.crm.service.CustomerService;
import com.java.crm.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

    //模型驱动使用的对象
    private Customer customer = new Customer();

    @Override
    public Customer getModel() {
        return customer;
    }
    //注入Service
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    //使用set方法的方式接收数据
    private Integer currPage = 1;

    public void setCurrPage(Integer currPage) {
        if(currPage == null){
            currPage = 1;
        }
        this.currPage = currPage;
    }

    //使用set方法的方式接受数据
    private Integer pageSize = 3;

    public void setPageSize(Integer pageSize) {
        if(pageSize == null){
            pageSize = 1;
        }
        this.pageSize = pageSize;
    }



    /**
     * 客户管理：跳转到添加页面的方法：saveUI
     * @return
     */
    public String saveUI(){
        return "saveUI";
    }

    private String uploadFileName; //文件名称
    private File upload;    //上传文件
    private String uploadContentType;   //文件类型

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    /**
     * 保存客户的方法：save
     */
    public String save() throws IOException {
        //上传图片
        if(upload!=null){
            //文件上传
            //设置文件上传路径：
            String path = "D:/java/upload";
            //解决一个目录下存放相同的文件名：随机文件名
            String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
            //解决目录下存放的文件过多：目录分离
            String realPath = UploadUtils.getPath(uuidFileName);
            //创建目录
            String url = path + realPath;
            File file = new File(url);
            if(!file.exists()){
                file.mkdirs();
            }
            //文件上传
            File dictFile = new File(url+"/"+uuidFileName);
            FileUtils.copyFile(upload,dictFile);

            //设置image的属性的值：
            customer.setCustImage(url+"/"+uuidFileName);

        }
       customerService.save(customer);
       return "saveSuccess";
    }

    /**
     * 查询客户列表
     * @return
     */
    public String findAll(){
        //接受参数：分页参数
        //最好使用DetachedCriteria对象（条件查询--带分页）
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
        //设置条件(在web层设置条件)
        if(customer.getCustName() != null){
            //输入名称：
            detachedCriteria.add(Restrictions.like("custName","%" + customer.getCustName() + "%"));
        }
        if(customer.getBaseDictLevel() != null){
            if(customer.getBaseDictLevel().getDictId() != null
                    && !"".equals(customer.getBaseDictLevel().getDictId())){
                detachedCriteria
                        .add(Restrictions.eq("baseDictLevel.dictId",customer.getBaseDictLevel().getDictId()));
            }

        }
        if(customer.getBaseDictSource() != null){
            if(customer.getBaseDictSource().getDictId() != null
                    && !"".equals(customer.getBaseDictSource().getDictId())){
                detachedCriteria
                        .add(Restrictions.eq("baseDictSource.dictId",customer.getBaseDictSource().getDictId()));
            }

        }
        if(customer.getBaseDictIndustry() != null){
            if(customer.getBaseDictIndustry().getDictId() != null
                    && !"".equals(customer.getBaseDictIndustry().getDictId())){
                detachedCriteria
                        .add(Restrictions.eq("baseDictIndustry.dictId",customer.getBaseDictIndustry().getDictId()));
            }

        }

        //调用业务层查询
        PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria,currPage,pageSize);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }

    /**
     * 删除客户的方法：delete
     * @return
     */
    public String delete(){
        //先查询，再删除，可以保证级联删除，和查询出图片路径删除
        customer = customerService.findById(customer.getCustId());
        //删除图片
        if(customer.getCustImage() !=null){
            File file = new File(customer.getCustImage());
            if(file.exists()){
                file.delete();
            }
        }
        //删除客户
        customerService.delete(customer);
        return "deleteSuccess";
    }

    /**
     * 编辑客户的方法：edit
     */
    public String edit(){
        //根据id查询，跳转页面，回显数据
        customer = customerService.findById(customer.getCustId());
        //将customer传递到页面
        //两种方式，一种，手动压栈，二，因为模型驱动的对象，默认在栈顶
        //如果用第一种方式：回显数据 <s:property value="custName"/> <s:name="custName" value="">
        //第二种方式:回显数据    <s:property value="model.custName"/>
//        ActionContext.getContext().getValueStack().push(customer);
        //跳转页面
        return "editSuccess";
    }

    /**
     * 修改客户的方法：update
     */
    public String update() throws IOException {
        // 文件项是否已经选择：如果选择了，就删除原有文件，上传新文件。如果没有选，使用原有即可。
        if(upload != null){
            //已经选择了
            //删除源文件
            String custImage =customer.getCustImage();
            if(custImage != null || !"".equals(custImage)){
                File file = new File(custImage);
                file.delete();
            }
            //文件上传
            //设置文件上传路径：
            String path = "D:/java/upload";
            //解决一个目录下存放相同的文件名：随机文件名
            String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
            //解决目录下存放的文件过多：目录分离
            String realPath = UploadUtils.getPath(uuidFileName);
            //创建目录
            String url = path + realPath;
            File file = new File(url);
            if(!file.exists()){
                file.mkdirs();
            }
            //文件上传
            File dictFile = new File(url+"/"+uuidFileName);
            FileUtils.copyFile(upload,dictFile);

            //设置image的属性的值：
            customer.setCustImage(url+"/"+uuidFileName);
        }
        customerService.update(customer);
        return "updateSuccess";
    }

    public String findAllCustomer() throws IOException{
        List<Customer> list = customerService.findAll();
        // 将list转成JSON:
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"linkMans","baseDictSource","baseDictLevel","baseDictIndustry"});
        // 转成JSOn:
        JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return NONE;
    }


}
