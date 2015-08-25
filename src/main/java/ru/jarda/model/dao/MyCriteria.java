package ru.jarda.model.dao;

/**
 * Created by User on 24.08.2015.
 */
public class MyCriteria {
   private String type;
    private String field;
    private Object value;

    public MyCriteria(String type, String field, Object value) {
        this.type = type;
        this.field = field;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public String getField() {
        return field;
    }
}
