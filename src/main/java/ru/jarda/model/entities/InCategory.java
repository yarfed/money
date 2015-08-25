package ru.jarda.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by User on 24.08.2015.
 */
@Entity
@Table(name = "in_categories")
public class InCategory extends Category {


    public InCategory() {
        super();
    }


}