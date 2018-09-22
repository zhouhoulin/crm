package com.java.crm.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cst_linkman", schema = "crm")
public class LinkMan {
    private long lkmId;
    private String lkmName;
    private String lkmGender;
    private String lkmPhone;
    private String lkmMobile;
    private String lkmEmail;
    private String lkmQq;
    private String lkmPosition;
    private String lkmMemo;
    // 在联系人方放置客户的对象:
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Id
    @Column(name = "lkm_id")
    public long getLkmId() {
        return lkmId;
    }

    public void setLkmId(long lkmId) {
        this.lkmId = lkmId;
    }

    @Basic
    @Column(name = "lkm_name")
    public String getLkmName() {
        return lkmName;
    }

    public void setLkmName(String lkmName) {
        this.lkmName = lkmName;
    }

    @Basic
    @Column(name = "lkm_gender")
    public String getLkmGender() {
        return lkmGender;
    }

    public void setLkmGender(String lkmGender) {
        this.lkmGender = lkmGender;
    }

    @Basic
    @Column(name = "lkm_phone")
    public String getLkmPhone() {
        return lkmPhone;
    }

    public void setLkmPhone(String lkmPhone) {
        this.lkmPhone = lkmPhone;
    }

    @Basic
    @Column(name = "lkm_mobile")
    public String getLkmMobile() {
        return lkmMobile;
    }

    public void setLkmMobile(String lkmMobile) {
        this.lkmMobile = lkmMobile;
    }

    @Basic
    @Column(name = "lkm_email")
    public String getLkmEmail() {
        return lkmEmail;
    }

    public void setLkmEmail(String lkmEmail) {
        this.lkmEmail = lkmEmail;
    }

    @Basic
    @Column(name = "lkm_qq")
    public String getLkmQq() {
        return lkmQq;
    }

    public void setLkmQq(String lkmQq) {
        this.lkmQq = lkmQq;
    }

    @Basic
    @Column(name = "lkm_position")
    public String getLkmPosition() {
        return lkmPosition;
    }

    public void setLkmPosition(String lkmPosition) {
        this.lkmPosition = lkmPosition;
    }

    @Basic
    @Column(name = "lkm_memo")
    public String getLkmMemo() {
        return lkmMemo;
    }

    public void setLkmMemo(String lkmMemo) {
        this.lkmMemo = lkmMemo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkMan linkman = (LinkMan) o;
        return lkmId == linkman.lkmId &&
                Objects.equals(lkmName, linkman.lkmName) &&
                Objects.equals(lkmGender, linkman.lkmGender) &&
                Objects.equals(lkmPhone, linkman.lkmPhone) &&
                Objects.equals(lkmMobile, linkman.lkmMobile) &&
                Objects.equals(lkmEmail, linkman.lkmEmail) &&
                Objects.equals(lkmQq, linkman.lkmQq) &&
                Objects.equals(lkmPosition, linkman.lkmPosition) &&
                Objects.equals(lkmMemo, linkman.lkmMemo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(lkmId, lkmName, lkmGender, lkmPhone, lkmMobile, lkmEmail, lkmQq, lkmPosition, lkmMemo);
    }
}
