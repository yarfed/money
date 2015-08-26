package ru.jarda.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * Created by User on 26.08.2015.
 */
@Entity
@Table(name = "property")
public class Property extends CommonEntity {
   @Column
    private String name;

    @Column
    private String value;
    public Property() {
       super();
    }
    public Property(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
