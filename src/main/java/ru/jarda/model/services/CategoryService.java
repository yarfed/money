package ru.jarda.model.services;


import org.springframework.transaction.annotation.Transactional;
import ru.jarda.model.dao.Dao;
import ru.jarda.model.dao.MyCriteria;
import ru.jarda.model.entities.Category;
import ru.jarda.model.entities.CommonEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by user on 14.02.2015.
 */
@Transactional
public class CategoryService {
    private Dao<Category> categories;
    public void setCategories(Dao<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getAll(){
     List<MyCriteria> criterias = new ArrayList<>();
        criterias.add(new MyCriteria("=","userId",new CommonEntity().getUserId()));
        return categories.getAllObjects(criterias);
    }

    public void add(Category category) {
        int order = getNextOrderInCategory(category.getParentId());
        category.setOrder(order);
        categories.saveObject(category);
    }

    private Integer getNextOrderInCategory(long id){
        List<MyCriteria> criterias = new ArrayList<>();
        criterias.add(new MyCriteria("=","parentId",id));
        criterias.add(new MyCriteria("=","userId",new CommonEntity().getUserId()));
        return categories.getObjectCount(criterias).intValue();
    }

    public void delete(long id){
        Category category=categories.getObject(id);
        long parentId=category.getParentId();
        categories.deleteObject(id);
        List<Category> brothersList = getChildren(parentId);
        List<Category> childrenList = getChildren(id);
        Collections.sort(brothersList);
        Collections.sort(childrenList);
        brothersList.addAll(childrenList);
        for (Category c:childrenList){
            c.setParentId(parentId);
        }
        int i=0;
        for (Category c:brothersList){
            c.setOrder(i++);
            categories.editObject(c);
        }
    }

    private List<Category> getChildren(long id){
        List<MyCriteria> criterias = new ArrayList<>();
        criterias.add(new MyCriteria("=","parentId",id));
        criterias.add(new MyCriteria("=","userId",new CommonEntity().getUserId()));
        return categories.getAllObjects(criterias);
    }

    public void edit(Category category){
        Category oldCategory=categories.getObject(category.getId());
        long oldParentId=oldCategory.getParentId();
        long parentId=category.getParentId();
        if (oldParentId!=parentId){
            isHeir(category.getId(), parentId);
            category.setOrder(getNextOrderInCategory(parentId));
            categories.saveObject(category);
            List<Category> brothersList = getChildren(oldParentId);
            int i=0;
            for (Category c:brothersList){
                c.setOrder(i++);
                categories.saveObject(c);
            }
        } else categories.saveObject(category);

    }

   private void isHeir(long id, long parentId){
    if (id==parentId) throw new RuntimeException("can't do this");
      if (getNextOrderInCategory(id)>0){
          List<Category> childrenList = getChildren(id);
          for (Category c:childrenList){
            isHeir(c.getId(), parentId);
          }
      }
   }


}