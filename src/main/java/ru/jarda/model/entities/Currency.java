package ru.jarda.model.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by User on 25.08.2015.
 */

@Entity
@Table(name = "currency")
public class Currency extends CommonEntity {
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private String symbol;
    @Column
    private float course;
    @Column(name="upd")
    private boolean update;
    @Column
    private Boolean basic;

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

    public float getCourse() {
        return course;
    }

    public void setCourse(float course) {
        this.course = course;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public Boolean isBasic() {
        return basic;
    }

    public void setBasic(Boolean basic) {
        this.basic = basic;
    }
}
