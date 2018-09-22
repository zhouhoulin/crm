package com.java.crm.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "base_dict", schema = "crm")
public class BaseDict {
    private String dictId;
    private String dictTypeCode;
    private String dictTypeName;
    private String dictItemName;
    private String dictItemCode;
    private Integer dictSort;
    private String dictEnable;
    private String dictMemo;

    @Id
    @Column(name = "dict_id")
    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    @Basic
    @Column(name = "dict_type_code")
    public String getDictTypeCode() {
        return dictTypeCode;
    }

    public void setDictTypeCode(String dictTypeCode) {
        this.dictTypeCode = dictTypeCode;
    }

    @Basic
    @Column(name = "dict_type_name")
    public String getDictTypeName() {
        return dictTypeName;
    }

    public void setDictTypeName(String dictTypeName) {
        this.dictTypeName = dictTypeName;
    }

    @Basic
    @Column(name = "dict_item_name")
    public String getDictItemName() {
        return dictItemName;
    }

    public void setDictItemName(String dictItemName) {
        this.dictItemName = dictItemName;
    }

    @Basic
    @Column(name = "dict_item_code")
    public String getDictItemCode() {
        return dictItemCode;
    }

    public void setDictItemCode(String dictItemCode) {
        this.dictItemCode = dictItemCode;
    }

    @Basic
    @Column(name = "dict_sort")
    public Integer getDictSort() {
        return dictSort;
    }

    public void setDictSort(Integer dictSort) {
        this.dictSort = dictSort;
    }

    @Basic
    @Column(name = "dict_enable")
    public String getDictEnable() {
        return dictEnable;
    }

    public void setDictEnable(String dictEnable) {
        this.dictEnable = dictEnable;
    }

    @Basic
    @Column(name = "dict_memo")
    public String getDictMemo() {
        return dictMemo;
    }

    public void setDictMemo(String dictMemo) {
        this.dictMemo = dictMemo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDict baseDict = (BaseDict) o;
        return Objects.equals(dictId, baseDict.dictId) &&
                Objects.equals(dictTypeCode, baseDict.dictTypeCode) &&
                Objects.equals(dictTypeName, baseDict.dictTypeName) &&
                Objects.equals(dictItemName, baseDict.dictItemName) &&
                Objects.equals(dictItemCode, baseDict.dictItemCode) &&
                Objects.equals(dictSort, baseDict.dictSort) &&
                Objects.equals(dictEnable, baseDict.dictEnable) &&
                Objects.equals(dictMemo, baseDict.dictMemo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dictId, dictTypeCode, dictTypeName, dictItemName, dictItemCode, dictSort, dictEnable, dictMemo);
    }
}
