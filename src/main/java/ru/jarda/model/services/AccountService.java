package ru.jarda.model.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.jarda.model.dao.Dao;
import ru.jarda.model.dao.MyCriteria;
import ru.jarda.model.entities.Account;
import ru.jarda.model.entities.CommonEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 02.02.2015.
 */
@Component
@Transactional
public class AccountService {
    @Autowired
    Dao<Account> accountsDao;

    public List<Account> getAll(){
        List<MyCriteria> criterias = new ArrayList<>();
        criterias.add(new MyCriteria("=","userId",new CommonEntity().getUserId()));
        return accountsDao.getAllObjects(criterias);
    }

    public void add(Account account) {
            accountsDao.saveObject(account);
    }

    public void delete(long id){
        accountsDao.deleteObject(id);

    }
    public void change(long id,int diff){
        Account account= accountsDao.getObject(id);
        account.setValue(account.getValue() + diff);
        accountsDao.saveObject(account);
    }

}
