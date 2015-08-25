package ru.jarda.model.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by user on 14.02.2015.
 */
@MappedSuperclass
public  class Category extends CommonEntity implements Comparable <Category>{
   @Column
    private String name;
    @Column(name="parent_id")
    private long  parentId;
    @Column(name="order_in")
    private Integer order;


    public Category() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (getId() != category.getId()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }


    @Override
    public int compareTo(Category category){
        return this.getOrder()-category.getOrder();
    }

}
