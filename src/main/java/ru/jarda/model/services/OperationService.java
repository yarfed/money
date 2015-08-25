package ru.jarda.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.jarda.model.dao.Dao;
import ru.jarda.model.dao.MyCriteria;
import ru.jarda.model.entities.CommonEntity;
import ru.jarda.model.entities.Operation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 13.07.2015.
 */
@Component
@Transactional
public class OperationService {
    @Autowired
    private Dao<Operation> operationDao;

    @Autowired
    private AccountService accounts;

    public List<Operation> getAll(){
        List<MyCriteria> criterias = new ArrayList<>();
        criterias.add(new MyCriteria("=","userId",new CommonEntity().getUserId()));
        return operationDao.getAllObjects(criterias);
    }

    public void add(Operation operation) {
        Long id=operation.getId();
        if (id!=null){
            Operation oldOperation= operationDao.getObject(id);
            revertOperation(oldOperation);
        }
        applyOperation(operation);
        operationDao.saveObject(operation);
    }

    public void delete(Long id) {
        Operation operation= operationDao.getObject(id);
        revertOperation(operation);
        operationDao.deleteObject(operation);
    }
    // i know that this look like doubled code below, but i think it more clearly to understanding
    private void applyOperation(Operation operation){
        if (operation.getInAccountId()!=null) {
            accounts.change(operation.getInAccountId(),operation.getInValue());
        }
        if (operation.getOutAccountId()!=null) {
            accounts.change(operation.getOutAccountId(),-operation.getOutValue());
        }
    }

    private void revertOperation(Operation operation){
        if (operation.getInAccountId()!=null) {
            accounts.change(operation.getInAccountId(),-operation.getInValue());
        }
        if (operation.getOutAccountId()!=null) {
            accounts.change(operation.getOutAccountId(),operation.getOutValue());
        }
    }
}
