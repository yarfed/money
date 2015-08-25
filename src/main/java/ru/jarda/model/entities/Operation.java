package ru.jarda.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by User on 09.08.2015.
 */
@Entity
@Table(name = "operations")
public class Operation extends CommonEntity {
    @Column
    private long date;
    @Column
    private String type;
    @Column(name="in_category_id")
    private Long inCategoryId;
    @Column(name="in_account_id")
    private Long inAccountId;
    @Column(name="in_value")
    private Integer inValue;
    @Column(name="out_category_id")
    private Long outCategoryId;
    @Column(name="out_account_id")
    private Long outAccountId;
    @Column(name="out_value")
    private Integer outValue;

    private String comment;


    public Operation() {
            super();
    }
  public Operation(Number id){
      super();
      setId((Long)id);
  }
    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getInCategoryId() {
        return inCategoryId;
    }

    public void setInCategoryId(Long inCategoryId) {
        this.inCategoryId = inCategoryId;
    }

    public Long getInAccountId() {
        return inAccountId;
    }

    public void setInAccountId(Long inAccountId) {
        this.inAccountId = inAccountId;
    }

    public Integer getInValue() {
        return inValue;
    }

    public void setInValue(Integer inValue) {
        this.inValue = inValue;
    }

    public Long getOutCategoryId() {
        return outCategoryId;
    }

    public void setOutCategoryId(Long outCategoryId) {
        this.outCategoryId = outCategoryId;
    }

    public Long getOutAccountId() {
        return outAccountId;
    }

    public void setOutAccountId(Long outAccountId) {
        this.outAccountId = outAccountId;
    }

    public Integer getOutValue() {
        return outValue;
    }

    public void setOutValue(Integer outValue) {
        this.outValue = outValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}