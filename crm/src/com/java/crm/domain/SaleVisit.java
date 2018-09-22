package com.java.crm.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "sale_visit", schema = "crm")
public class SaleVisit {
    private String visitId;
    private Timestamp visitTime;
    private String visitAddr;
    private String visitDetail;
    private Date visitNexttime;

    //拜访记录关联的用户的对象
    private User user;
    //拜访记录关联的客户的对象
    private Customer customer;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Id
    @Column(name = "visit_id")
    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    @Basic
    @Column(name = "visit_time")
    public Timestamp getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Timestamp visitTime) {
        this.visitTime = visitTime;
    }

    @Basic
    @Column(name = "visit_addr")
    public String getVisitAddr() {
        return visitAddr;
    }

    public void setVisitAddr(String visitAddr) {
        this.visitAddr = visitAddr;
    }

    @Basic
    @Column(name = "visit_detail")
    public String getVisitDetail() {
        return visitDetail;
    }

    public void setVisitDetail(String visitDetail) {
        this.visitDetail = visitDetail;
    }

    @Basic
    @Column(name = "visit_nexttime")
    public Date getVisitNexttime() {
        return visitNexttime;
    }

    public void setVisitNexttime(Date visitNexttime) {
        this.visitNexttime = visitNexttime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleVisit saleVisit = (SaleVisit) o;
        return Objects.equals(visitId, saleVisit.visitId) &&
                Objects.equals(visitTime, saleVisit.visitTime) &&
                Objects.equals(visitAddr, saleVisit.visitAddr) &&
                Objects.equals(visitDetail, saleVisit.visitDetail) &&
                Objects.equals(visitNexttime, saleVisit.visitNexttime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(visitId, visitTime, visitAddr, visitDetail, visitNexttime);
    }
}
