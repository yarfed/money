package ru.jarda.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.jarda.model.dao.UserDaoJdbcImpl;
import ru.jarda.model.entities.CustomUser;


/**
 * Created by User on 04.06.2015.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private UserDaoJdbcImpl userDao;


    @Override
    public UserDetails loadUserByUsername(String name)
            throws UsernameNotFoundException {

        return userDao.get(name);
    }

    public void add (String username, String password){

        CustomUser user = new CustomUser(-1,username,password);
        userDao.saveObject(user);
    }
}
