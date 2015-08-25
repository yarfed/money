package ru.jarda.model.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by user on 26.01.2015.
 */

@Entity
@Table(name = "accounts")
public class Account extends CommonEntity{
   @Column
    private String name;
    @Column
    private int value;
    @Column
    private String currency;

    public Account() {
         super();
    }
    public Account(Long id){
        setId(id);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
