package com.java.crm.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cst_customer", schema = "crm")
public class Customer {
    private long custId;
    private String custName;
//    private String custSource;
//    private String custIndustry;
//    private String custLevel;
    private String custPhone;
    private String custMobile;
    private String custImage;//客户资质的图片

    public String getCustImage() {
        return custImage;
    }

    public void setCustImage(String custImage) {
        this.custImage = custImage;
    }

    /**
     * 客户和字典表示多对一：需要在多的一方放的是一的一方的对象
     * @return
     */
    private BaseDict baseDictSource;
    private BaseDict baseDictIndustry;
    private BaseDict baseDictLevel;

    //一个客户有多个联系人
    private Set<LinkMan> linkMans = new HashSet<LinkMan>();
    public Set<LinkMan> getLinkMans() {
        return linkMans;
    }

    public void setLinkMans(Set<LinkMan> linkMans) {
        this.linkMans = linkMans;
    }

    @Id
    @Column(name = "cust_id")
    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    @Basic
    @Column(name = "cust_name")
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    @Basic
    @Column(name = "cust_phone")
    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    @Basic
    @Column(name = "cust_mobile")
    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile;
    }

    public BaseDict getBaseDictSource() {
        return baseDictSource;
    }

    public void setBaseDictSource(BaseDict baseDictSource) {
        this.baseDictSource = baseDictSource;
    }

    public BaseDict getBaseDictIndustry() {
        return baseDictIndustry;
    }

    public void setBaseDictIndustry(BaseDict baseDictIndustry) {
        this.baseDictIndustry = baseDictIndustry;
    }

    public BaseDict getBaseDictLevel() {
        return baseDictLevel;
    }

    public void setBaseDictLevel(BaseDict baseDictLevel) {
        this.baseDictLevel = baseDictLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return custId == customer.custId &&
                Objects.equals(custName, customer.custName) &&
                Objects.equals(custPhone, customer.custPhone) &&
                Objects.equals(custMobile, customer.custMobile) &&
                Objects.equals(baseDictSource, customer.baseDictSource) &&
                Objects.equals(baseDictIndustry, customer.baseDictIndustry) &&
                Objects.equals(baseDictLevel, customer.baseDictLevel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(custId, custName, custPhone, custMobile, baseDictSource, baseDictIndustry, baseDictLevel);
    }
}
