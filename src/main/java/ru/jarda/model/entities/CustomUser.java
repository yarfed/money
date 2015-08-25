package ru.jarda.model.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 04.06.2015.
 */
public class CustomUser implements UserDetails
{
    private int userID;
    public int getUserID() {
        return userID;
    }
    private String username;
    private String password;

    public CustomUser(int id, String username, String password)
    {

        this.userID = id;
        this.username = username;
        this.password = password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority auth = new GrantedAuthority() {

            public String getAuthority() {
                return "ROLE_USER";
            }
        };
        Set<GrantedAuthority> set = new HashSet<>();
        set.add(auth);
        return set;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

}