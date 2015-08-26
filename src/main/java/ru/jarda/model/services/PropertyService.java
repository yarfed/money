package ru.jarda.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.jarda.model.dao.Dao;
import ru.jarda.model.dao.MyCriteria;
import ru.jarda.model.entities.CommonEntity;
import ru.jarda.model.entities.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26.08.2015.
 */

@Component
public class PropertyService {
    @Autowired
    private Dao<Property> propertyDao;

    public Property getByName (String name){
        List<MyCriteria> criterias = new ArrayList<>();
        criterias.add(new MyCriteria("=","userId",new CommonEntity().getUserId()));
        criterias.add(new MyCriteria("=","name",name));
        ArrayList<Property> propertyList = (ArrayList)propertyDao.getAllObjects(criterias);
       if (propertyList.size()>0) {
           return propertyList.get(0);
       } else return null;
    }

    public void setProperty(String name, String value){
        Property property=getByName(name);
        if (property==null) {
            property=new Property(name);
        }
        property.setValue(value);
        propertyDao.saveObject(property);
    }
}
