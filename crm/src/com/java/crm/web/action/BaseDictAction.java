package com.java.crm.web.action;

import com.java.crm.domain.BaseDict;
import com.java.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.List;

/**
 * BaseDicr的Action类
 */
public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {

    //模型驱动使用的对象
    private BaseDict baseDict = new BaseDict();

    @Override
    public BaseDict getModel() {
        return baseDict;
    }

    //注入Service
    private BaseDictService baseDictService;

    public void setBaseDictService(BaseDictService baseDictService) {
        this.baseDictService = baseDictService;
    }

    /**
     * 根据类型名称查询字典的方法：findByTypeCode
     */
    public String findByTypeCode() throws IOException {
        System.out.println("BaseDictAction中的findByTypeCode被执行了...");
        //调用业务层查询
        List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDictTypeCode());
        //将list转成JSON --- jsonlib fastjson

        /**
         * JSONConfig:转成JSON的配置对象
         * JSONArray:将数据和lsit结合转成JSON
         * JSONObject:将对象和Map集合转成JSON
         */
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"dictSort","dictEnable","dictMemo"});
        JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
        System.out.println(jsonArray.toString());
        //将JSON打印到页面
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return NONE;
    }
}
