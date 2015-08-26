package ru.jarda.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.jarda.model.dao.Dao;
import ru.jarda.model.dao.MyCriteria;
import ru.jarda.model.entities.CommonEntity;
import ru.jarda.model.entities.Currency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26.08.2015.
 */
    @Component
    @Transactional
    public class CurrencyService {
        @Autowired
        Dao<Currency> currencyDao;

        public List<Currency> getAll(){
            List<MyCriteria> criterias = new ArrayList<>();
            criterias.add(new MyCriteria("=","userId",new CommonEntity().getUserId()));
            return currencyDao.getAllObjects(criterias);
        }

        public void add(Currency currency) {
            currencyDao.saveObject(currency);
        }

        public void delete(long id){
            currencyDao.deleteObject(id);
        }

}
