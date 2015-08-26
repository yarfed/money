package ru.jarda.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by User on 25.08.2015.
 */
@Entity
public class Currency extends CommonEntity {
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private String symbol;
    @Column
    private int course;
    @Column
    private boolean update;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
}
