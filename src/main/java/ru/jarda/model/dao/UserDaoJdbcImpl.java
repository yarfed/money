package ru.jarda.model.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import ru.jarda.model.entities.CustomUser;

import java.util.List;
import java.util.Map;


/**
 * Created by User on 04.06.2015.
 */
public class UserDaoJdbcImpl extends JdbcDaoSupport implements Dao<CustomUser>{


    public CustomUser get(String username){

        String sql = "SELECT * FROM users WHERE username=?";
        int id;
        String password;

        Map<String, Object> row = getJdbcTemplate().queryForMap(sql, username);
        password = (String)row.get("password");
        id = (int)row.get("id");

        return new CustomUser(id,username,password);
    }

    @Override
    public void saveObject(CustomUser user){
        String username = user.getUsername();
        String password = user.getPassword();
       String sql = "insert into users (username, password) values (\'"+
               username +"\', \'" + password + "\'"  + ")";
        System.out.println(sql);
       getJdbcTemplate().update(sql);


    }
    @Override
    public CustomUser getObject(Number id){
        return null;
    }
    @Override
    public List<CustomUser> getAllObjects(List list){
        return null;
    }

    @Override
    public void editObject(CustomUser user){

    }
    @Override
    public void deleteObject(Number id){

    }

    @Override
    public void deleteObject(CustomUser entity) {

    }

    @Override
    public Integer getObjectCount(List<MyCriteria> criteriaList) {
        return null;
    }
}
