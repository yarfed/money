package ru.jarda.model.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by User on 24.08.2015.
 */
@Transactional
public class DaoHibernateImpl<T> implements Dao<T> {
    @Autowired
    SessionFactory sessionFactory;
    private Class<T> type;

    public DaoHibernateImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public void saveObject(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(entity);
    }

    @Override
    public void deleteObject(Number id) {
        Session session = sessionFactory.getCurrentSession();
        Object entity=session.load(type,id);
        session.delete(entity);
    }

    @Override
    public void deleteObject(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(entity);
    }

    @Override
    public T getObject(Number id) {
        Session session = sessionFactory.getCurrentSession();
        return (T)session.get(type,id);
    }

    @Override
    public List<T> getAllObjects(List<MyCriteria> criteriaList) {
        Criteria criteria = getCriteria(criteriaList);
        return criteria.list();
    }

    private Criteria getCriteria(List<MyCriteria> criteriaList) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(type);

        for (MyCriteria myCriteria:criteriaList){
           switch(myCriteria.getType()){
               case "=":  criteria.add(Restrictions.eq(myCriteria.getField(), myCriteria.getValue()));
                   break;

           }
       }
        return criteria;
    }

    @Override
    public void editObject(T entity) {

    }
    @Override
    public Number getObjectCount(List<MyCriteria> criteriaList){
        Criteria criteria = getCriteria(criteriaList);
         return (Number)criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

}
