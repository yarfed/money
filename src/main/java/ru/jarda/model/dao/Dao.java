package ru.jarda.model.dao;

import java.util.List;

/**
 * Created by User on 13.07.2015.
 */

   public interface Dao<T> {
          public void saveObject(T entity);

          public void deleteObject(Number id);
    public void deleteObject(T entity);
          public T getObject(Number id);
          public List<T> getAllObjects(List<MyCriteria> list);
          public void editObject(T entity);
          public Number getObjectCount(List<MyCriteria> criteriaList);
        //  public List<T> getObjects(String request, Object[] values);

    }
